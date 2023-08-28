package com.example.application.domain;

public class Mentee extends Participant {
    public Mentee(String name, String email, Boolean connected) {
        this.name = name;
        this.email = email;
        this.connected = connected;
    }
    public void connect() {
        if (!connected) {
            //find a mentee
            //based on technologies chosen
            //display the mentee's name and email'
        }
    }
    public Mentee() {

    }
}
