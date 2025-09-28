package com.dormwars.dormwarsapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sports")
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sportId;

    private String sportName;
    @Column(columnDefinition = "text")
    private String description;
    private String abbreviation;
    private Integer playersPerTeam;

    public Long getSportId() { return sportId; }
    public void setSportId(Long sportId) { this.sportId = sportId; }
    public String getSportName() { return sportName; }
    public void setSportName(String sportName) { this.sportName = sportName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getAbbreviation() { return abbreviation; }
    public void setAbbreviation(String abbreviation) { this.abbreviation = abbreviation; }
    public Integer getPlayersPerTeam() { return playersPerTeam; }
    public void setPlayersPerTeam(Integer playersPerTeam) { this.playersPerTeam = playersPerTeam; }
}
