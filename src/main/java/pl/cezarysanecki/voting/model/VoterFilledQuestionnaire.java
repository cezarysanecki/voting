package pl.cezarysanecki.voting.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(schema = "voting", name = "voter_filled_questionnaire")
public class VoterFilledQuestionnaire {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @OneToOne
  @JoinColumn(name = "questionnaire_id")
  private VoteQuestionnaire voteQuestionnaire;

  @OneToMany
  @JoinColumn(name = "answered_question_id")
  private List<AnsweredQuestion> answeredQuestions;

}
