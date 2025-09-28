package com.dormwars.dormwarsapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    private String teamName;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    private Boolean active = true;

    public Long getTeamId() { return teamId; }
    public void setTeamId(Long teamId) { this.teamId = teamId; }
    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }
    public School getSchool() { return school; }
    public void setSchool(School school) { this.school = school; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
