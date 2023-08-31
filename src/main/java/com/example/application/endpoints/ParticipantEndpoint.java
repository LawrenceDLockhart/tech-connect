package com.example.application.endpoints;

import com.example.application.domain.Participant;
import com.example.application.repositories.ParticipantRepository;
import dev.hilla.Endpoint;
import dev.hilla.Nonnull;

import java.util.List;

@Endpoint
public class ParticipantEndpoint {
    private ParticipantRepository participantRepository;

    public ParticipantEndpoint(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public @Nonnull List<@Nonnull Participant> findAll() {
        return participantRepository.findAll();
    }

    public Participant save(Participant participant) {
        return participantRepository.save(participant);
    }

}
