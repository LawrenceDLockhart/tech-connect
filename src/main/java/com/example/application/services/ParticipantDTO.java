package com.example.application.services;


public class ParticipantDTO {
    private Long id;
    private String userName;
    private String email;
    private String technologies="None selected";
    private String mentorOrMentee = "Not set";


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

    public String getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }

    public String getMentorOrMentee() {
        return mentorOrMentee;
    }

    public void setMentorOrMentee(String mentorOrMentee) {
        this.mentorOrMentee = mentorOrMentee;
    }
}
