package pl.cezarysanecki.voting.repository;

import org.springframework.data.repository.CrudRepository;
import pl.cezarysanecki.voting.model.Question;
import pl.cezarysanecki.voting.model.QuestionAnswer;

public interface QuestionRepository extends CrudRepository<Question, Long> {
}
