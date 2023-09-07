package com.example.application.domain;

import jakarta.persistence.GeneratedValue;

import java.util.Optional;

public class ParticipantDTO {
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private Optional<Technology> technology;
    public enum Technology {
        PYTHON,
        JAVA,
        JAVASCRIPT
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Optional<Technology> getTechnology() {
        technology = Optional.empty();
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = Optional.empty();
    }

    public ParticipantDTO convertToDTO(Participant participant) {
        ParticipantDTO dto = new ParticipantDTO();
        dto.setId(participant.getId());
        dto.setName(participant.getName());
        dto.setEmail(participant.getEmail());
        return dto;
    }
}
