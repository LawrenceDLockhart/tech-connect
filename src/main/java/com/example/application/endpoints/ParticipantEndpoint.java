package com.example.application.endpoints;

import com.example.application.domain.Participant;
import com.example.application.repositories.ParticipantRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.Endpoint;

import java.util.List;

@Endpoint
@AnonymousAllowed
public class ParticipantEndpoint {
    private ParticipantRepository participantRepository;

    public ParticipantEndpoint(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public List<Participant> findAll() {
        return participantRepository.findAll();
    }

    public Participant save(Participant participant) {
        return participantRepository.save(participant);
    }

}
