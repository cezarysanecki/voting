package pl.cezarysanecki.voting.repository;

import org.springframework.data.repository.CrudRepository;
import pl.cezarysanecki.voting.model.QuestionAnswer;

public interface QuestionAnswerRepository extends CrudRepository<QuestionAnswer, Long> {
}
