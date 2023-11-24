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
import pl.cezarysanecki.voting.dto.UpdateVoteQuestionnaireDto;
import pl.cezarysanecki.voting.dto.GetVoteQuestionnaireDto;
import pl.cezarysanecki.voting.service.VoteQuestionnaireService;

import java.util.List;

@RestController
@RequestMapping("/voting/proposals")
@RequiredArgsConstructor
public class ProposalVotesController {

  private final VoteQuestionnaireService voteQuestionnaireService;

  @PostMapping
  GetVoteQuestionnaireDto createQuestionnaire(@RequestBody CreateVoteQuestionnaireDto createVoteQuestionnaireDto) {
    return voteQuestionnaireService.createQuestionnaire(createVoteQuestionnaireDto);
  }

  @PutMapping("/{id}")
  GetVoteQuestionnaireDto editQuestionnaire(@PathVariable Long id, @RequestBody UpdateVoteQuestionnaireDto updateVoteQuestionnaireDto) {
    return voteQuestionnaireService.editQuestionnaire(id, updateVoteQuestionnaireDto);
  }

  @DeleteMapping("/{id}")
  void deleteProposal(@PathVariable Long id) {
    voteQuestionnaireService.deleteQuestionnaire(id);
  }

  @GetMapping("/{id}")
  GetVoteQuestionnaireDto getProposal(@PathVariable Long id) {
    return voteQuestionnaireService.getQuestionnaire(id);
  }

  @GetMapping
  List<GetVoteQuestionnaireDto> getProposals() {
    return voteQuestionnaireService.getQuestionnaires();
  }

}
