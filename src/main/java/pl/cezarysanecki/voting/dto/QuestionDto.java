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
public class QuestionDto {

  String text;
  List<QuestionAnswerDto> answers;

}
