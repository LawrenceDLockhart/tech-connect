package com.example.application.services;

import com.example.application.domain.Participant;
import com.example.application.domain.Technology;
import com.example.application.repositories.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConnectService {

    private ParticipantRepository participantRepository;

    public void updateTechnology(Long id, Technology technology) {
         Optional<Participant> optionalParticipant = participantRepository.findById(id);
         if (optionalParticipant.isPresent()) {
             Participant participant = optionalParticipant.get();
             participant.setSelectedTechnology(technology);
             participantRepository.save(participant);
         }
    }

    private void updateConnection(Long id) {
        System.out.println("Connection completed");
    }
}
