package com.example.application.domain;

import jakarta.persistence.GeneratedValue;

import java.util.Optional;

public class ParticipantDTO {
    @GeneratedValue
    private Long id;
    private String userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        dto.setUserName(participant.getUserName());
        dto.setEmail(participant.getEmail());
        return dto;
    }
}
