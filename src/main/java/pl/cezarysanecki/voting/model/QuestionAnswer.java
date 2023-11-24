package pl.cezarysanecki.voting.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import pl.cezarysanecki.voting.dto.QuestionAnswerDto;

@Data
@Entity
@Table(schema = "voting", name = "question_answer")
public class QuestionAnswer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "value")
  private String value;

  @ManyToOne
  @JoinColumn(name = "question_id")
  private Question question;

  public QuestionAnswerDto toDto() {
    return QuestionAnswerDto.builder()
        .value(value)
        .build();
  }

}
