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
import pl.cezarysanecki.voting.dto.EditVoteQuestionnaireDto;
import pl.cezarysanecki.voting.dto.VoteQuestionnaireDto;
import pl.cezarysanecki.voting.dto.VoteQuestionnaireWithoutQuestionsDto;
import pl.cezarysanecki.voting.service.VoteQuestionnaireService;

import java.util.List;

@RestController
@RequestMapping("/voting/proposals")
@RequiredArgsConstructor
public class ProposalVotesController {

  private final VoteQuestionnaireService voteQuestionnaireService;

  @PostMapping("/party/{partyId}")
  VoteQuestionnaireDto createQuestionnaire(
      @PathVariable("partyId") Long partyId,
      @RequestBody CreateVoteQuestionnaireDto createVoteQuestionnaireDto) {
    return voteQuestionnaireService.createQuestionnaire(partyId, createVoteQuestionnaireDto);
  }

  @PutMapping("/{questionnaireId}/party/{partyId}")
  VoteQuestionnaireDto editQuestionnaire(
      @PathVariable("questionnaireId") Long questionnaireId,
      @PathVariable("partyId") Long partyId,
      @RequestBody EditVoteQuestionnaireDto editVoteQuestionnaireDto) {
    return voteQuestionnaireService.editQuestionnaire(partyId, questionnaireId, editVoteQuestionnaireDto);
  }

  @DeleteMapping("/{questionnaireId}/party/{partyId}")
  void deleteProposal(@PathVariable("questionnaireId") Long questionnaireId, @PathVariable("partyId") Long partyId) {
    voteQuestionnaireService.deleteQuestionnaire(partyId, questionnaireId);
  }

  @GetMapping("/{questionnaireId}/party/{partyId}")
  VoteQuestionnaireDto getProposal(
      @PathVariable("questionnaireId") Long questionnaireId,
      @PathVariable("partyId") Long partyId) {
    return voteQuestionnaireService.getQuestionnaire(partyId, questionnaireId);
  }

  @GetMapping
  List<VoteQuestionnaireWithoutQuestionsDto> getProposals() {
    return voteQuestionnaireService.getQuestionnaires();
  }

}
