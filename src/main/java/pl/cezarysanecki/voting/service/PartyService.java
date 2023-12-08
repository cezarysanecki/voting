package pl.cezarysanecki.voting.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;
import pl.cezarysanecki.voting.dto.CreatePartyDto;
import pl.cezarysanecki.voting.dto.EditPartyDto;
import pl.cezarysanecki.voting.dto.PartyDto;
import pl.cezarysanecki.voting.model.Party;
import pl.cezarysanecki.voting.repository.PartyRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartyService {

  private final PartyRepository partyRepository;

  public PartyDto createParty(CreatePartyDto createPartyDto) {
    Party party = new Party();
    party.setEmail(createPartyDto.getEmail());
    party.setNickname(createPartyDto.getNickname());
    party.setActive(true);
    party.setQuestionnaires(List.of());
    party.setFilledQuestionnaires(List.of());
    party.setRegistered(LocalDateTime.now());

    Party saved = partyRepository.save(party);

    return saved.toDto();
  }

  public PartyDto editParty(Long partyId, EditPartyDto editPartyDto) {
    Party party = partyRepository.findById(partyId)
        .orElseThrow(() -> new IllegalStateException("cannot find party by id: " + partyId));

    if (!party.isActive()) {
      throw new IllegalArgumentException("cannot edit inactive party");
    }

    party.setNickname(editPartyDto.getNickname());

    return party.toDto();
  }

  public void deleteParty(Long partyId) {
    Party party = partyRepository.findById(partyId)
        .orElseThrow(() -> new IllegalStateException("cannot find party by id: " + partyId));

    if (!party.isActive()) {
      throw new IllegalStateException("cannot deactivate already inactive user");
    }

    party.setActive(false);
  }

  public PartyDto getParty(Long partyId) {
    return partyRepository.findById(partyId)
        .map(Party::toDto)
        .orElseThrow(() -> new IllegalStateException("cannot find party by id: " + partyId));
  }

  public List<PartyDto> getParties() {
    return IterableUtils.toList(partyRepository.findAll())
        .stream()
        .map(Party::toDto)
        .toList();
  }

}
