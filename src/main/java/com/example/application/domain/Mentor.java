package com.example.application.domain;

public class Mentor extends Participant {
    public Mentor(String name, String email, Boolean connected) {
        this.name = name;
        this.email = email;
        this.connected = connected;
    }
    public void connect() {
        if (!connected) {

            //find a mentor
            //based on technologies chosen
            //display the mentor's name and email'
        }
    }
    public Mentor() {

    }
}
