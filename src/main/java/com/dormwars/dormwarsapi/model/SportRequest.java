package com.dormwars.dormwarsapi.model;

public class SportRequest {
    private String sportName;
    private String description;
    private String abbreviation;
    private Integer playersPerTeam;

    public String getSportName() { return sportName; }
    public void setSportName(String sportName) { this.sportName = sportName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getAbbreviation() { return abbreviation; }
    public void setAbbreviation(String abbreviation) { this.abbreviation = abbreviation; }
    public Integer getPlayersPerTeam() { return playersPerTeam; }
    public void setPlayersPerTeam(Integer playersPerTeam) { this.playersPerTeam = playersPerTeam; }
}
