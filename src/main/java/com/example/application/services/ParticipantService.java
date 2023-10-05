package com.example.application.services;

import com.example.application.domain.Participant;
import com.example.application.domain.Technology;
import com.example.application.repositories.ParticipantRepository;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.BrowserCallable;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@BrowserCallable
@PermitAll
@Service
public class ParticipantService {

    private PasswordEncoder passwordEncoder;
    private ParticipantRepository repository;

    public ParticipantService(ParticipantRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @DenyAll
    public void createParticipant(Participant participant) {
        participant.setPassword(passwordEncoder.encode(participant.getPassword()));
        repository.save(participant);
    }

    public ParticipantDTO getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Participant participant = repository.findByUserName(currentPrincipalName)
                .orElseThrow();
        return convertToDTO(participant);
    }
//    public void updateTechnology(Long participantId, List<Technology> technologies) {
//        Optional<Participant> participantOptional = repository.findById(participantId);
//        if (participantOptional.isPresent()) {
//            Participant participant = participantOptional.get();
//            participant.setTechnologies(technologies);
//            repository.save(participant);
//        } else {
//            System.out.println("cannot connect technolgoy to "+ participantId );
//            // change above to display in browser
//        }
//    }
    // Service method to connect a mentor to a mentee
//    @Transactional
//    public void connectMentorAndMentee(Long mentorId, int index) {
//        Optional<Participant> mentorOptional = repository.findById(mentorId);
//        if (mentorOptional.isPresent()) {
//            Participant mentor = mentorOptional.get();
//            List<Participant> potentialMentees = repository.findAllByTechnologiesAndMentorIsNull(mentor.getTechnology(index));
//            if (!potentialMentees.isEmpty()) {
//                Participant mentee = potentialMentees.get(0);
//                mentee.setMentor(mentor);
//
//                if (mentor.getMentees() == null) {
//                    mentor.setMentees(new ArrayList<>());
//                }
//
//                mentor.getMentees().add(mentee);
//
//                repository.save(mentee);
//                repository.save(mentor);
//            } else {
//                // Handle no available mentees
//            }
//        } else {
//            // Handle mentor not found
//        }
//    }
    public ParticipantDTO convertToDTO(Participant participant){
        ParticipantDTO dto = new ParticipantDTO();
        dto.setId(participant.getId());
        dto.setUserName(participant.getUserName());
        dto.setEmail(participant.getEmail());
        dto.setTechnologies(participant.getTechnologies());
        return dto;
    }
    public Participant convertToParticipant(ParticipantDTO dto){
        Participant participant = new Participant();
        participant.setId(dto.getId());
        participant.setUserName(dto.getUserName());
        participant.setEmail(dto.getEmail());
        return participant;
    }

    public List<ParticipantDTO> findAll() {
        List<Participant> participants = repository.findAll();
        return participants.stream().map(this::convertToDTO).toList();
    }

    public ParticipantDTO save(ParticipantDTO participantDTO) {
        Participant participant = convertToParticipant(participantDTO);
        Participant savedParticipant = repository.save(participant);
        System.out.println("Saving participant " + participant);
        return convertToDTO(savedParticipant);
    }
}

