package com.example.application.endpoints;

import com.example.application.domain.Participant;
import com.example.application.domain.ParticipantDTO;
import com.example.application.repositories.ParticipantRepository;
import com.example.application.services.ParticipantService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.BrowserCallable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@BrowserCallable
@Service
@AnonymousAllowed
public class ParticipantEndpoint {

    private ParticipantRepository participantRepository;
    private ParticipantService participantService;
    public ParticipantEndpoint(ParticipantRepository participantRepository, ParticipantService participantService) {
        this.participantRepository = participantRepository;
        this.participantService = participantService;
    }

    public List<ParticipantDTO> findAll() {
        return participantService.findAll();
    }
    public Long getLoggedInUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Participant participant = participantRepository.findByUserName(currentPrincipalName)
                .orElse(null);

        if (participant != null) {
            return participant.getId();
        }
        return null;
    }

    public Participant save(Participant participant) {

        Long loggedInUserId = getLoggedInUserId();
        Optional<Participant> optionalExistingParticipant = participantRepository.findById(loggedInUserId);

        if (optionalExistingParticipant.isPresent()) {
            Participant existingParticipant = optionalExistingParticipant.get();
            if(participant.getMentor() != null) {
                existingParticipant.setMentor(participant.getMentor());
            }
            existingParticipant.setTechnologies(participant.getTechnologies());
            existingParticipant.setMentor(participant.getMentor());
            return participantRepository.save(existingParticipant);
        } else {
            // logged in participant doesn't exist??
        }
        return null;
    }


}
