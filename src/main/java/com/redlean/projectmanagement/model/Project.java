package com.redlean.projectmanagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "project", schema = "public")
public class Project implements Serializable {

    @Id
    @GeneratedValue(generator = "project_generator", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "project_generator", sequenceName = "project_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "sprintNumber")
    private int sprintNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "delivery")
    private Date delivery;

    @Column(name = "leaderName")
    private String projectLeaderName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", nullable = false)
    @JsonBackReference
    private Team team;

    public Project() {
    }

    public Project(String title, String description, int sprintNumber, Date delivery, String projectLeaderName, Team team) {
        this.title = title;
        this.description = description;
        this.sprintNumber = sprintNumber;
        this.delivery = delivery;
        this.projectLeaderName = projectLeaderName;
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSprintNumber() {
        return sprintNumber;
    }

    public void setSprintNumber(int sprintNumber) {
        this.sprintNumber = sprintNumber;
    }

    public Date getDelivery() {
        return delivery;
    }

    public void setDelivery(Date delivery) {
        this.delivery = delivery;
    }

    public String getProjectLeaderName() {
        return projectLeaderName;
    }

    public void setProjectLeaderName(String projectLeaderName) {
        this.projectLeaderName = projectLeaderName;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
