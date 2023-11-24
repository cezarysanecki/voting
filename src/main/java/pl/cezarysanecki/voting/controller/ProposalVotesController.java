package pl.cezarysanecki.voting.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.cezarysanecki.voting.service.ProposalVotesService;

@RestController
@RequestMapping("/voting/proposal")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ProposalVotesController {

  private final ProposalVotesService proposalVotesService;

  @PostMapping
  void createProposal() {
    proposalVotesService.createProposal();
  }

  @PutMapping
  void editProposal() {
    proposalVotesService.editProposal();
  }

  @DeleteMapping
  void deleteProposal() {
    proposalVotesService.deleteProposal();
  }

  @GetMapping
  void getProposal() {
    proposalVotesService.getProposal();
  }

  @GetMapping
  void getProposals() {
    proposalVotesService.getProposals();
  }

}
