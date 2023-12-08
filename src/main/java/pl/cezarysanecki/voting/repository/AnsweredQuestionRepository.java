package pl.cezarysanecki.voting.repository;

import org.springframework.data.repository.CrudRepository;
import pl.cezarysanecki.voting.model.AnsweredQuestion;
import pl.cezarysanecki.voting.model.Question;

public interface AnsweredQuestionRepository extends CrudRepository<AnsweredQuestion, Long> {
}
