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

@Service
@RequiredArgsConstructor
public class VoteQuestionnaireService {

  private final PartyRepository partyRepository;
  private final VoteQuestionnaireRepository voteQuestionnaireRepository;

  public VoteQuestionnaireDto createQuestionnaire(
      Long partyId,
      CreateVoteQuestionnaireDto createVoteQuestionnaireDto) {
    Party party = partyRepository.findById(partyId)
        .orElseThrow(() -> new IllegalStateException("cannot find party by id: " + partyId));

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
    VoteQuestionnaire foundVoteQuestionnaire = voteQuestionnaireRepository.findById(questionnaireId)
        .orElseThrow(() -> new IllegalStateException("cannot find questionnaire by id: " + questionnaireId));

    if (!foundVoteQuestionnaire.getCreator().getId().equals(partyId)) {
      throw new IllegalArgumentException("only creator can edit questionnaire");
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

    voteQuestionnaireRepository.delete(foundVoteQuestionnaire);
  }

  public VoteQuestionnaireDto getQuestionnaire(Long partyId, Long questionnaireId) {
    VoteQuestionnaire foundVoteQuestionnaire = voteQuestionnaireRepository.findById(questionnaireId)
        .orElseThrow(() -> new IllegalStateException("cannot find questionnaire by id: " + questionnaireId));

    if (!foundVoteQuestionnaire.getCreator().getId().equals(partyId)) {
      throw new IllegalArgumentException("only creator can edit questionnaire");
    }

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
