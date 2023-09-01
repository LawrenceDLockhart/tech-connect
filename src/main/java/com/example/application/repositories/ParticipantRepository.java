package com.example.application.repositories;

import com.example.application.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findAllByTechnologyAndMentorIsNull(Participant.Technology technology);
}
