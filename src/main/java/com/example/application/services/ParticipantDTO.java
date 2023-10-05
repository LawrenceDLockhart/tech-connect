package com.example.application.domain;

import jakarta.persistence.GeneratedValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParticipantDTO {
    @GeneratedValue
    private Long id;
    private String userName;
    private String email;
    List<String> technologies = new ArrayList<String>();
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

    public ParticipantDTO convertToDTO(Participant participant) {
        ParticipantDTO dto = new ParticipantDTO();
        dto.setId(participant.getId());
        dto.setUserName(participant.getUserName());
        dto.setEmail(participant.getEmail());
        return dto;
    }
}
