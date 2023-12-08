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
import pl.cezarysanecki.voting.dto.CreatePartyDto;
import pl.cezarysanecki.voting.dto.EditPartyDto;
import pl.cezarysanecki.voting.dto.PartyDto;
import pl.cezarysanecki.voting.service.PartyService;

import java.util.List;

@RestController
@RequestMapping("/voting/parties")
@RequiredArgsConstructor
public class PartyController {

  private final PartyService partyService;

  @PostMapping
  PartyDto createParty(@RequestBody CreatePartyDto createPartyDto) {
    return partyService.createParty(createPartyDto);
  }

  @PutMapping("/{partyId}")
  PartyDto editParty(@PathVariable("partyId") Long partyId, @RequestBody EditPartyDto editPartyDto) {
    return partyService.editParty(partyId, editPartyDto);
  }

  @DeleteMapping("/{partyId}")
  void deleteProposal(@PathVariable("partyId") Long partyId) {
    partyService.deleteParty(partyId);
  }

  @GetMapping("/{partyId}")
  PartyDto getParty(@PathVariable("partyId") Long partyId) {
    return partyService.getParty(partyId);
  }

  @GetMapping
  List<PartyDto> getParties() {
    return partyService.getParties();
  }

}
