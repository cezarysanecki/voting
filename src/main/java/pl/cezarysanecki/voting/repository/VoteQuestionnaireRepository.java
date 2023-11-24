package pl.cezarysanecki.voting.repository;

import org.springframework.data.repository.CrudRepository;
import pl.cezarysanecki.voting.model.VoteQuestionnaire;

public interface VoteQuestionnaireRepository extends CrudRepository<VoteQuestionnaire, Long> {
}
