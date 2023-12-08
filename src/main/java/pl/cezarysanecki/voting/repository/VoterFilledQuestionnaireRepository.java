package pl.cezarysanecki.voting.repository;

import org.springframework.data.repository.CrudRepository;
import pl.cezarysanecki.voting.model.VoteQuestionnaire;
import pl.cezarysanecki.voting.model.VoterFilledQuestionnaire;

public interface VoterFilledQuestionnaireRepository extends CrudRepository<VoterFilledQuestionnaire, Long> {
}
