package com.example.application.repositories;

import com.example.application.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findAllByTechnologyAndMentorIsNull(Participant.Technology technology);
    Optional<Participant> findByUserName(String userName);
}
