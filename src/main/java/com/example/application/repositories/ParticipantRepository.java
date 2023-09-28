package com.example.application.repositories;

import com.example.application.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    @Query("SELECT p FROM Participant p WHERE :technology MEMBER OF p.technologies AND p.mentor IS NULL")

    List<Participant> findAllByTechnologiesAndMentorIsNull(String technology);
    Optional<Participant> findByUserName(String userName);
}
