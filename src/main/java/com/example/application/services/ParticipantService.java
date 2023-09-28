package com.example.application.services;

import com.example.application.domain.Participant;
import com.example.application.domain.ParticipantDTO;
import com.example.application.repositories.ParticipantRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {

    private PasswordEncoder passwordEncoder;
    private ParticipantRepository repository;
    public ParticipantService(ParticipantRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createParticipant(Participant participant) {
        participant.setPassword(passwordEncoder.encode(participant.getPassword()));
        repository.save(participant);
    }
    public void updateTechnology(Long participantId, List<String> technologies) {
        Optional<Participant> participantOptional = repository.findById(participantId);
        if (participantOptional.isPresent()) {
            Participant participant = participantOptional.get();
            participant.setTechnologies(technologies);
            repository.save(participant);
        } else {
            System.out.println("cannot connect technolgoy to "+ participantId );
            // change above to display in browser
        }
    }
    // Service method to connect a mentor to a mentee
    @Transactional
    public void connectMentorAndMentee(Long mentorId, int index) {
        Optional<Participant> mentorOptional = repository.findById(mentorId);
        if (mentorOptional.isPresent()) {
            Participant mentor = mentorOptional.get();
            List<Participant> potentialMentees = repository.findAllByTechnologiesAndMentorIsNull(mentor.getTechnology(index));
            if (!potentialMentees.isEmpty()) {
                Participant mentee = potentialMentees.get(0);
                mentee.setMentor(mentor);

                if (mentor.getMentees() == null) {
                    mentor.setMentees(new ArrayList<>());
                }

                mentor.getMentees().add(mentee);

                repository.save(mentee);
                repository.save(mentor);
            } else {
                // Handle no available mentees
            }
        } else {
            // Handle mentor not found
        }
    }
    public ParticipantDTO convertToDTO(Participant participant){
        ParticipantDTO dto = new ParticipantDTO();
        dto.setId(participant.getId());
        dto.setUserName(participant.getUserName());
        dto.setEmail(participant.getEmail());
        return dto;
    }

    public List<ParticipantDTO> findAll() {
        List<Participant> participants = repository.findAll();
        return participants.stream().map(this::convertToDTO).toList();
    }

}

