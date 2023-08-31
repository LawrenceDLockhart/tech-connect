package com.example.application.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Id;
import jakarta.persistence.Enumerated;

@Entity
public class Participant {
    String name;
    @Id
    Long id;

    public Boolean getMentor() {
        return mentor;
    }

    public void setMentor(Boolean mentor) {
        this.mentor = mentor;
    }

    public Technology getSelectedTechnology() {
        return selectedTechnology;
    }

    public void setSelectedTechnology(Technology selectedTechnology) {
        this.selectedTechnology = selectedTechnology;
    }

    String email;
    public Boolean mentor;
    public Boolean connected;
    @Enumerated(EnumType.STRING)
    private Technology selectedTechnology;

    public Participant() {
    }

    public String getName() {
        return name;
    }

    public Participant(String name, String email, Boolean connected) {
        this.name = name;
        this.email = email;
        this.connected = connected;
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

    public Boolean getConnected() {
        return connected;
    }

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }



}
