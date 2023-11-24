package pl.cezarysanecki.voting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.cezarysanecki.voting.dto.CreateVoteQuestionnaireDto;
import pl.cezarysanecki.voting.dto.VoteQuestionnaireDto;
import pl.cezarysanecki.voting.model.VoteQuestionnaire;
import pl.cezarysanecki.voting.service.VoteQuestionnaireService;

import java.util.List;

@RestController
@RequestMapping("/voting/proposals")
@RequiredArgsConstructor
public class ProposalVotesController {

  private final VoteQuestionnaireService voteQuestionnaireService;

  @PostMapping
  VoteQuestionnaireDto createQuestionnaire(@RequestBody CreateVoteQuestionnaireDto createVoteQuestionnaireDto) {
    return voteQuestionnaireService.createQuestionnaire(createVoteQuestionnaireDto);
  }

  @PutMapping("/{id}")
  VoteQuestionnaireDto editQuestionnaire(@PathVariable Long id, @RequestBody VoteQuestionnaireDto voteQuestionnaire) {
    return voteQuestionnaireService.editQuestionnaire(id, voteQuestionnaire);
  }

  @DeleteMapping("/{id}")
  void deleteProposal(@PathVariable Long id) {
    voteQuestionnaireService.deleteQuestionnaire(id);
  }

  @GetMapping("/{id}")
  VoteQuestionnaireDto getProposal(@PathVariable Long id) {
    return voteQuestionnaireService.getQuestionnaire(id);
  }

  @GetMapping
  List<VoteQuestionnaireDto> getProposals() {
    return voteQuestionnaireService.getQuestionnaires();
  }

}
