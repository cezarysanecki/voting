package pl.cezarysanecki.voting.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;
import pl.cezarysanecki.voting.model.VoteQuestionnaire;
import pl.cezarysanecki.voting.repository.VoteQuestionnaireRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteQuestionnaireService {

  private final VoteQuestionnaireRepository voteQuestionnaireRepository;

  public VoteQuestionnaire createQuestionnaire(VoteQuestionnaire voteQuestionnaire) {
    if (voteQuestionnaire.getId() != null) {
      throw new IllegalStateException("cannot persist questionnaire with id");
    }

    voteQuestionnaire.setCreationDateTime(LocalDateTime.now());
    voteQuestionnaire.setReadyToVote(false);

    return voteQuestionnaireRepository.save(voteQuestionnaire);
  }

  public VoteQuestionnaire editQuestionnaire(final Long id, VoteQuestionnaire voteQuestionnaire) {
    VoteQuestionnaire foundVoteQuestionnaire = voteQuestionnaireRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException("cannot find questionnaire for id: " + id));

    foundVoteQuestionnaire.getQuestions().clear();
    foundVoteQuestionnaire.getQuestions().addAll(voteQuestionnaire.getQuestions());

    return voteQuestionnaireRepository.save(foundVoteQuestionnaire);
  }

  public void deleteQuestionnaire(Long id) {
    voteQuestionnaireRepository.deleteById(id);
  }

  public VoteQuestionnaire getQuestionnaire(Long id) {
    return voteQuestionnaireRepository.findById(id)
        .orElseThrow(() -> new IllegalStateException("cannot find questionnaire for id: " + id));
  }

  public List<VoteQuestionnaire> getQuestionnaires() {
    return IterableUtils.toList(voteQuestionnaireRepository.findAll());
  }

}
