package pl.cezarysanecki.voting.repository;

import org.springframework.data.repository.CrudRepository;
import pl.cezarysanecki.voting.model.Party;
import pl.cezarysanecki.voting.model.VoteQuestionnaire;

import java.util.Optional;

public interface PartyRepository extends CrudRepository<Party, Long> {

  Optional<Party> findByIdAndActiveIsTrue(Long partyId);

}
