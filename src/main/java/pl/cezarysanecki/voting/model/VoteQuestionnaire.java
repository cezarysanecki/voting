package pl.cezarysanecki.voting.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(schema = "voting", name = "vote_questionnaire")
public class VoteQuestionnaire {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Question> questions;

  @Column(name = "creation_date_time")
  private LocalDateTime creationDateTime;

  @Column(name = "ready_to_vote")
  private boolean readyToVote;

  @Column(name = "voting_expiry_date_time")
  private LocalDateTime votingExpiryDateTime;

}
