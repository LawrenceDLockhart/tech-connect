package com.example.application.services;

import com.example.application.domain.Participant;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateParticipant implements CommandLineRunner {
    private final ParticipantService participantService;
    public CreateParticipant(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @Override
    public void run(String... args) {
        if(participantService.findAll().isEmpty()) {
            participantService.createParticipant(new Participant(1L,"user1", "password1", "user1@example.com"));
            participantService.createParticipant(new Participant(2L,"user2", "password2", "user2@example.com"));
            participantService.createParticipant(new Participant(3L,"user3", "password3", "user3@example.com"));
        }
    }
}
