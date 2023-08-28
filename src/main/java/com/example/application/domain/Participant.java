package com.example.application.domain;

public class Participant {
    String name;
    Integer id;
    String email;
    Boolean connected;

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
