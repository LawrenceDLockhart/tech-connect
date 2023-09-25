package com.example.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"mentor"})
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Technology technology;
    @ManyToOne // Many mentees can have one mentor
    @JoinColumn(name = "mentor_id")
    private Participant mentor;
    @JsonManagedReference
    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL) // One mentor can have many mentees
    private List<Participant> mentees;
    public Participant() {
    }
    public Participant(String email, Technology technology) {
        this.email = email;
        this.technology = technology;
    }
    public Participant(String userName, String password, String email) {
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

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public enum Technology {
        PYTHON,
        JAVA,
        JAVASCRIPT
    }

}
