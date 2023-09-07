package com.example.application.endpoints;

import com.example.application.domain.Participant;
import com.example.application.domain.ParticipantDTO;
import com.example.application.repositories.ParticipantRepository;
import com.example.application.services.ParticipantService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.Endpoint;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
@Endpoint
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
