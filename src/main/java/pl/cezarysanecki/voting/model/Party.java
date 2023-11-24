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
@Table(schema = "voting", name = "party")
public class Party {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  private String email;

  private String nickname;

  private boolean active;

  private LocalDateTime registered;

  @OneToMany(cascade = CascadeType.ALL)
  private List<VoteQuestionnaire> questionnaires;

  @OneToMany(cascade = CascadeType.ALL)
  private List<VoterFilledQuestionnaire> filledQuestionnaires;

}
