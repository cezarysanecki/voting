package pl.cezarysanecki.voting.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(schema = "voting", name = "answered_question")
public class AnsweredQuestion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "filled_questionnaire_id")
  private VoterFilledQuestionnaire filledQuestionnaire;

  @OneToOne
  @JoinColumn(name = "question_id")
  private Question question;

  @OneToOne
  @JoinColumn(name = "answer_id")
  private QuestionAnswer answer;

}
