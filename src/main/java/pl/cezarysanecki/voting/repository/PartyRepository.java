package pl.cezarysanecki.voting.repository;

import org.springframework.data.repository.CrudRepository;
import pl.cezarysanecki.voting.model.Party;
import pl.cezarysanecki.voting.model.VoteQuestionnaire;

public interface PartyRepository extends CrudRepository<Party, Long> {
}