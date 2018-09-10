package com.redlean.projectmanagement.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "team", schema = "public")
public class Team implements Serializable{

    @Id
    //@GeneratedValue(generator = "team_generator", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "team_generator", sequenceName = "team_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = EAGER, mappedBy = "team")
    @JsonManagedReference
    private Set<Member> members;

    @OneToMany(cascade = CascadeType.ALL, fetch = EAGER, mappedBy = "team")
    @JsonManagedReference
    private Set<Project> projects;

    public Team() {
        projects = new HashSet<>();
        members = new HashSet<>();
    }

    public Team(Long id, Set<Member> members, Set<Project> projects, Team team) {
        this.id = id;
        this.members = members;
        this.projects = projects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
