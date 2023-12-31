package com.example.application.services;

import com.example.application.domain.Participant;
import com.example.application.repositories.ParticipantRepository;
import dev.hilla.BrowserCallable;
import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

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
        // Check if authentication object is null
        if (authentication == null) {
            throw new IllegalStateException("No authentication available.");
        }
        String currentPrincipalName = authentication.getName();
        // Check if principal name is empty or null
        if (currentPrincipalName == null || currentPrincipalName.isEmpty()) {
            throw new IllegalStateException("Principal name is empty.");
        }
        // Find the participant, throw an exception if not found
        Participant participant = repository.findByUserName(currentPrincipalName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        // Assume that convertToDTO checks for null and handles it appropriately
        return convertToDTO(participant);
    }

    public ParticipantDTO convertToDTO(Participant participant){
        ParticipantDTO dto = new ParticipantDTO();
        dto.setId(participant.getId());
        dto.setUserName(participant.getUserName());
        dto.setEmail(participant.getEmail());
        dto.setTechnologies(participant.getTechnologies());
        dto.setMentorOrMentee(participant.getMentorOrMentee());
        return dto;
    }
    public Participant convertToParticipant(ParticipantDTO dto){
        Participant participant = new Participant();
        participant.setId(dto.getId());
        participant.setUserName(dto.getUserName());
        participant.setEmail(dto.getEmail());
        participant.setTechnologies(dto.getTechnologies());
        participant.setMentorOrMentee(dto.getMentorOrMentee());
        return participant;
    }

    public List<ParticipantDTO> findAll() {
        List<Participant> participants = repository.findAll();
        participants.forEach(participant -> {
            System.out.println("Participant ID: " + participant.getId());
            System.out.println("Technologies: " + participant.getTechnologies());  // log technologies here
        });
        return participants.stream().map(this::convertToDTO).toList();
    }

    public ParticipantDTO save(ParticipantDTO participantDTO) {
        Participant participant = convertToParticipant(participantDTO);
        Participant savedParticipant = repository.save(participant);
        System.out.println("Saving participant " + participant);
        return convertToDTO(savedParticipant);
    }
}

