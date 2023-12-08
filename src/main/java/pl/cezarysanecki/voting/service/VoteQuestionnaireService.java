package pl.cezarysanecki.voting.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;
import pl.cezarysanecki.voting.dto.CreateVoteQuestionnaireDto;
import pl.cezarysanecki.voting.dto.EditVoteQuestionnaireDto;
import pl.cezarysanecki.voting.dto.QuestionAnswerDto;
import pl.cezarysanecki.voting.dto.QuestionDto;
import pl.cezarysanecki.voting.dto.VoteQuestionnaireDto;
import pl.cezarysanecki.voting.dto.VoteQuestionnaireWithoutQuestionsDto;
import pl.cezarysanecki.voting.model.Party;
import pl.cezarysanecki.voting.model.Question;
import pl.cezarysanecki.voting.model.QuestionAnswer;
import pl.cezarysanecki.voting.model.VoteQuestionnaire;
import pl.cezarysanecki.voting.repository.PartyRepository;
import pl.cezarysanecki.voting.repository.VoteQuestionnaireRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class VoteQuestionnaireService {

  private final PartyRepository partyRepository;
  private final VoteQuestionnaireRepository voteQuestionnaireRepository;

  public VoteQuestionnaireDto createQuestionnaire(
      Long partyId,
      CreateVoteQuestionnaireDto createVoteQuestionnaireDto) {
    // maybe VIP flag?
    if (createVoteQuestionnaireDto.getQuestions().size() + 1 > 10) {
      throw new IllegalArgumentException("questionnaire cannot have more then 10 questions");
    }

    Party party = partyRepository.findByIdAndActiveIsTrue(partyId)
        .orElseThrow(() -> new IllegalStateException("cannot find party by id: " + partyId));

    if (party.getQuestionnaires()
        .stream()
        .filter(Predicate.not(VoteQuestionnaire::isReadyToVote))
        .filter(questionnaire -> questionnaire.getVotingExpiryDateTime() == null)
        .count() + 1 > 10) {
      throw new IllegalArgumentException("user cannot have more then 10 draft questionnaires");
    }

    VoteQuestionnaire entityVoteQuestionnaire = new VoteQuestionnaire();

    entityVoteQuestionnaire.setTitle(createVoteQuestionnaireDto.getTitle());
    entityVoteQuestionnaire.setReadyToVote(false);
    entityVoteQuestionnaire.setVotingExpiryDateTime(null);
    entityVoteQuestionnaire.setCreationDateTime(LocalDateTime.now());
    entityVoteQuestionnaire.setQuestions(mapQuestions(createVoteQuestionnaireDto.getQuestions(), entityVoteQuestionnaire));
    entityVoteQuestionnaire.setCreator(party);

    return voteQuestionnaireRepository.save(entityVoteQuestionnaire)
        .toDto();
  }

  public VoteQuestionnaireDto editQuestionnaire(
      Long partyId,
      Long questionnaireId,
      EditVoteQuestionnaireDto editVoteQuestionnaireDto) {
    if (editVoteQuestionnaireDto.getQuestions().size() + 1 > 10) {
      throw new IllegalArgumentException("questionnaire cannot have more then 10 questions");
    }

    VoteQuestionnaire foundVoteQuestionnaire = voteQuestionnaireRepository.findById(questionnaireId)
        .orElseThrow(() -> new IllegalStateException("cannot find questionnaire by id: " + questionnaireId));

    if (!foundVoteQuestionnaire.getCreator().getId().equals(partyId)) {
      throw new IllegalArgumentException("only creator can edit questionnaire");
    }
    if (foundVoteQuestionnaire.isReadyToVote()) {
      throw new IllegalArgumentException("cannot edit active questionnaire");
    }
    if (foundVoteQuestionnaire.getVotingExpiryDateTime() != null) {
      throw new IllegalArgumentException("cannot edit historical questionnaire");
    }

    foundVoteQuestionnaire.setTitle(editVoteQuestionnaireDto.getTitle());
    foundVoteQuestionnaire.setQuestions(
        mapQuestions(editVoteQuestionnaireDto.getQuestions(), foundVoteQuestionnaire));

    return voteQuestionnaireRepository.save(foundVoteQuestionnaire)
        .toDto();
  }

  public void deleteQuestionnaire(Long partyId, Long questionnaireId) {
    VoteQuestionnaire foundVoteQuestionnaire = voteQuestionnaireRepository.findById(questionnaireId)
        .orElseThrow(() -> new IllegalStateException("cannot find questionnaire by id: " + questionnaireId));

    if (!foundVoteQuestionnaire.getCreator().getId().equals(partyId)) {
      throw new IllegalArgumentException("only creator can edit questionnaire");
    }
    if (foundVoteQuestionnaire.isReadyToVote()) {
      throw new IllegalArgumentException("cannot delete active questionnaire");
    }
    if (foundVoteQuestionnaire.getVotingExpiryDateTime() != null) {
      throw new IllegalArgumentException("cannot delete archive questionnaire");
    }

    voteQuestionnaireRepository.delete(foundVoteQuestionnaire);
  }

  public VoteQuestionnaireDto getQuestionnaire(Long questionnaireId) {
    VoteQuestionnaire foundVoteQuestionnaire = voteQuestionnaireRepository.findById(questionnaireId)
        .orElseThrow(() -> new IllegalStateException("cannot find questionnaire by id: " + questionnaireId));

    return foundVoteQuestionnaire.toDto();
  }

  public List<VoteQuestionnaireWithoutQuestionsDto> getQuestionnaires() {
    return IterableUtils.toList(voteQuestionnaireRepository.findAll())
        .stream()
        .map(VoteQuestionnaire::toShortDto)
        .toList();
  }

  private static List<Question> mapQuestions(List<QuestionDto> questions, VoteQuestionnaire entityVoteQuestionnaire) {
    return questions.stream()
        .map(question -> {
          Question entityQuestion = new Question();

          entityQuestion.setText(question.getText());
          entityQuestion.setAnswers(mapAnswers(question.getAnswers(), entityQuestion));
          entityQuestion.setQuestionnaire(entityVoteQuestionnaire);

          return entityQuestion;
        })
        .toList();
  }

  private static List<QuestionAnswer> mapAnswers(List<QuestionAnswerDto> answers, Question entityQuestion) {
    return answers.stream()
        .map(answer -> {
          QuestionAnswer questionAnswer = new QuestionAnswer();

          questionAnswer.setValue(answer.getValue());
          questionAnswer.setQuestion(entityQuestion);

          return questionAnswer;
        })
        .toList();
  }

}
