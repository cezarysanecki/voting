package pl.cezarysanecki.voting.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;
import pl.cezarysanecki.voting.dto.CreateVoteQuestionnaireDto;
import pl.cezarysanecki.voting.dto.QuestionAnswerDto;
import pl.cezarysanecki.voting.dto.QuestionDto;
import pl.cezarysanecki.voting.dto.VoteQuestionnaireDto;
import pl.cezarysanecki.voting.model.Question;
import pl.cezarysanecki.voting.model.QuestionAnswer;
import pl.cezarysanecki.voting.model.VoteQuestionnaire;
import pl.cezarysanecki.voting.repository.VoteQuestionnaireRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteQuestionnaireService {

  private final VoteQuestionnaireRepository voteQuestionnaireRepository;

  public VoteQuestionnaireDto createQuestionnaire(CreateVoteQuestionnaireDto createVoteQuestionnaireDto) {
    VoteQuestionnaire entityVoteQuestionnaire = new VoteQuestionnaire();

    entityVoteQuestionnaire.setCreationDateTime(LocalDateTime.now());
    entityVoteQuestionnaire.setReadyToVote(false);
    entityVoteQuestionnaire.setVotingExpiryDateTime(createVoteQuestionnaireDto.getVotingExpiryDateTime());
    entityVoteQuestionnaire.setQuestions(mapQuestions(createVoteQuestionnaireDto.getQuestions(), entityVoteQuestionnaire));

    return voteQuestionnaireRepository.save(entityVoteQuestionnaire)
        .toDto();
  }

  public VoteQuestionnaireDto editQuestionnaire(final Long id, VoteQuestionnaireDto voteQuestionnaire) {
    VoteQuestionnaire foundVoteQuestionnaire = voteQuestionnaireRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException("cannot find questionnaire for id: " + id));

    foundVoteQuestionnaire.setReadyToVote(voteQuestionnaire.isReadyToVote());
    foundVoteQuestionnaire.setVotingExpiryDateTime(voteQuestionnaire.getVotingExpiryDateTime());

    foundVoteQuestionnaire.getQuestions().clear();
    foundVoteQuestionnaire.getQuestions().addAll(mapQuestions(voteQuestionnaire.getQuestions(), foundVoteQuestionnaire));

    return voteQuestionnaireRepository.save(foundVoteQuestionnaire)
        .toDto();
  }

  public void deleteQuestionnaire(Long id) {
    voteQuestionnaireRepository.deleteById(id);
  }

  public VoteQuestionnaireDto getQuestionnaire(Long id) {
    return voteQuestionnaireRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException("cannot find questionnaire for id: " + id))
        .toDto();
  }

  public List<VoteQuestionnaireDto> getQuestionnaires() {
    return IterableUtils.toList(voteQuestionnaireRepository.findAll())
        .stream()
        .map(VoteQuestionnaire::toDto)
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
