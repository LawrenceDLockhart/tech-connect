package com.example.application.services;

import com.example.application.domain.Participant;
import com.example.application.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    // Service method to update technology choice for a participant
    public void updateTechnology(Long participantId, Participant.Technology technology) {
        Optional<Participant> participantOptional = participantRepository.findById(participantId);
        if (participantOptional.isPresent()) {
            Participant participant = participantOptional.get();
            participant.setTechnology(technology);
            participantRepository.save(participant);
        } else {
            // Handle participant not found
        }
    }

    // Service method to connect a mentor to a mentee
    @Transactional
    public void connectMentorAndMentee(Long mentorId) {
        Optional<Participant> mentorOptional = participantRepository.findById(mentorId);
        if (mentorOptional.isPresent()) {
            Participant mentor = mentorOptional.get();

            List<Participant> potentialMentees = participantRepository.findAllByTechnologyAndMentorIsNull(mentor.getTechnology());
            if (!potentialMentees.isEmpty()) {
                Participant mentee = potentialMentees.get(0);
                mentee.setMentor(mentor);

                if (mentor.getMentees() == null) {
                    mentor.setMentees(new ArrayList<>());
                }

                mentor.getMentees().add(mentee);

                participantRepository.save(mentee);
                participantRepository.save(mentor);
            } else {
                // Handle no available mentees
            }
        } else {
            // Handle mentor not found
        }
    }
}

