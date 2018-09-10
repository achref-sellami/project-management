package com.redlean.projectmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="Member", schema = "public")
public class Member implements Serializable{

    @Id
    @SequenceGenerator(name = "member_generator", sequenceName = "member_sequence", allocationSize = 1)
    @GeneratedValue(generator = "member_generator", strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstName", length = 255)
    private String firstName;

    @Column(name = "lastName", length = 255)
    private String lastName;

    @Column(name = "role", length = 255)
    private String role;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", nullable = false)
    @JsonBackReference
    private Team team;

    public Member() {
    }

    public Member(Long id, String firstName, String lastName, String role, Team team) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
