package com.example.application.services;

import com.example.application.domain.Participant;
import com.example.application.repositories.ParticipantRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ParticipantDetailsService implements UserDetailsService {

    private ParticipantRepository participantRepository;
    public ParticipantDetailsService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Participant participant = participantRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return User.builder()
                .username(participant.getUserName())
                .password(participant.getPassword())
                .roles("USER")  // This can be dynamic based on your needs
                .build();
    }
}
