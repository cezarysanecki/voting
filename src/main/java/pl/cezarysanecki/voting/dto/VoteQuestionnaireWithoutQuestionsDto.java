package pl.cezarysanecki.voting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class VoteQuestionnaireWithoutQuestionsDto {

  Long id;
  String title;
  LocalDateTime creationDateTime;
  boolean readyToVote;
  LocalDateTime votingExpiryDateTime;

}
