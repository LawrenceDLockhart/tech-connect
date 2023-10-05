package com.example.application.services;

import com.example.application.domain.Participant;
import com.example.application.domain.Technology;
import jakarta.persistence.GeneratedValue;

import java.util.ArrayList;
import java.util.List;

public class ParticipantDTO {
    private Long id;
    private String userName;
    private String email;
    List<Technology> technologies = new ArrayList<>();

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

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }
}
