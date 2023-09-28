package com.example.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"mentor"})
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=3, max=50)
    private String userName;
    @Email
    private String email;
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "participant_technologies", joinColumns = @JoinColumn(name = "participant_id"))
    @Column(name = "technology")
    List<String> technologies = new ArrayList<String>();
    @ManyToOne // Many mentees can have one mentor
    @JoinColumn(name = "mentor_id")
    private Participant mentor;
    @JsonManagedReference
    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL) // One mentor can have many mentees
    private List<Participant> mentees;
    public Participant() {
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

    public List<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTechnology(int index) {
        return technologies.get(index);
    }

    public enum Technology {
        PYTHON,
        JAVA,
        JAVASCRIPT
    }

}
