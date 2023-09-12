package com.example.application.services;

import com.example.application.domain.Participant;
import com.example.application.domain.ParticipantDTO;
import com.example.application.repositories.ParticipantRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
@Repository
public class ParticipantService {

    private ParticipantRepository repository;
    public ParticipantService(ParticipantRepository repository) {
        this.repository = repository;
    }
    private ParticipantDTO participantDTO;

    public ParticipantDTO convertToDTO(Participant participant){
        ParticipantDTO dto = new ParticipantDTO();
        dto.setId(participant.getId());
        dto.setName(participant.getName());
        dto.setEmail(participant.getEmail());
        return dto;
    }

    public List<ParticipantDTO> findAll() {
        List<Participant> participants = repository.findAll();
        return participants.stream().map(this::convertToDTO).toList();
    }

}

