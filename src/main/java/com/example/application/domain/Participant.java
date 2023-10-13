package com.example.application.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonIgnoreProperties({"mentor"})
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Size(min=3, max=50)
    private String userName;
    @Email
    private String email;
    private String password;
    private String technologies = "None selected";

    @JsonBackReference
    @ManyToOne // Many mentees can have one mentor
    @JoinColumn(name = "mentor_id")
    private Participant mentor;

    @JsonManagedReference
    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL) // One mentor can have many mentees
    private List<Participant> mentees = new ArrayList<>();
    private String mentorOrMentee = "Not set";
    public String getMentorOrMentee() {
        return mentorOrMentee;
    }

    public void setMentorOrMentee(String mentorOrMentee) {
        this.mentorOrMentee = mentorOrMentee;
    }


    public Participant() {
    }
    public Participant(Long id, String userName, String password, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
    // Getters and Setters
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

    public Participant getMentor() {
        return mentor;
    }

    public void setMentor(Participant mentor) {
        this.mentor = mentor;
    }

    public List<Participant> getMentees() {
        return mentees;
    }

    public void setMentees(List<Participant> mentees) {
        this.mentees = mentees;
    }

    public String getTechnologies() {
        return technologies;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
