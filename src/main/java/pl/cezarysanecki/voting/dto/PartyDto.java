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
public class PartyDto {

  String email;
  String nickname;
  boolean active;
  List<VoteQuestionnaireDto> questionnaires;
  List<VoterFilledQuestionnaireDto> filledQuestionnaires;
  LocalDateTime registered;

}
