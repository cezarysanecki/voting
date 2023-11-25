package pl.cezarysanecki.voting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class VoteQuestionnaireDto {

  Long id;
  String title;
  List<QuestionDto> questions;
  LocalDateTime creationDateTime;
  boolean readyToVote;
  LocalDateTime votingExpiryDateTime;

}
