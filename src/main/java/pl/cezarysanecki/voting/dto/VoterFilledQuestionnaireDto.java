package pl.cezarysanecki.voting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class VoterFilledQuestionnaireDto {

  VoteQuestionnaireDto questionnaire;
  List<QuestionWithAnswerDto> questionsWithAnswers;

  @Value
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor(force = true)
  public static class QuestionWithAnswerDto {

    QuestionDto question;
    QuestionAnswerDto answer;

  }

}
