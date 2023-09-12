package com.example.application.endpoints;

import com.example.application.domain.Participant;
import com.example.application.domain.ParticipantDTO;
import com.example.application.repositories.ParticipantRepository;
import com.example.application.services.ParticipantService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.BrowserCallable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@BrowserCallable
@Service
@AnonymousAllowed
public class ParticipantEndpoint {

    private ParticipantRepository participantRepository;

    @Autowired
    private ParticipantService participantService;
    public ParticipantEndpoint(ParticipantRepository participantRepository, ParticipantService participantService) {
        this.participantRepository = participantRepository;
    }

    public List<ParticipantDTO> findAll() {
        return participantService.findAll().stream()
                .filter(Objects::nonNull)
                .toList();

    }

    public Participant save(Participant participant) {
        return participantRepository.save(participant);
    }

}
