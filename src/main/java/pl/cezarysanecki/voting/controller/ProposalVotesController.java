package pl.cezarysanecki.voting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.cezarysanecki.voting.model.VoteQuestionnaire;
import pl.cezarysanecki.voting.service.VoteQuestionnaireService;

import java.util.List;

@RestController
@RequestMapping("/voting/proposal")
@RequiredArgsConstructor
public class ProposalVotesController {

  private final VoteQuestionnaireService voteQuestionnaireService;

  @PostMapping
  VoteQuestionnaire createQuestionnaire(@RequestBody VoteQuestionnaire voteQuestionnaire) {
    return voteQuestionnaireService.createQuestionnaire(voteQuestionnaire);
  }

  @PutMapping("/{id}")
  VoteQuestionnaire editQuestionnaire(Long id, @RequestBody VoteQuestionnaire voteQuestionnaire) {
    return voteQuestionnaireService.editQuestionnaire(id, voteQuestionnaire);
  }

  @DeleteMapping("/{id}")
  void deleteProposal(Long id) {
    voteQuestionnaireService.deleteQuestionnaire(id);
  }

  @GetMapping("/{id}")
  VoteQuestionnaire getProposal(Long id) {
    return voteQuestionnaireService.getQuestionnaire(id);
  }

  @GetMapping
  List<VoteQuestionnaire> getProposals() {
    return voteQuestionnaireService.getQuestionnaires();
  }

}
