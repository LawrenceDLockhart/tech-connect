package com.example.application.services;

import com.example.application.domain.Participant;
import com.example.application.domain.Technology;
import com.example.application.repositories.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Connect {

    private ParticipantRepository participantRepository;

    private void updateTechnoglogy(Long id, Technology technology) {
         Optional<Participant> optionalParticipant = participantRepository.findById(id);
         if (optionalParticipant.isPresent()) {
             Participant participant = optionalParticipant.get();
             participant.setSelectedTechnology(technology);
             participantRepository.save(participant);
         }
    }

    private void updateConnection() {
        
    }
}
