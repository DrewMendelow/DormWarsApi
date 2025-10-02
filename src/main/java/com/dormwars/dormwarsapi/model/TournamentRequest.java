package com.dormwars.dormwarsapi.model;

public class TournamentRequest {
    private Long sportId;
    private Long winnerId;
    private Integer maxTeams;
    private Integer numberOfRounds;

    public Long getSportId() { return sportId; }
    public void setSportId(Long sportId) { this.sportId = sportId; }
    public Long getWinnerId() { return winnerId; }
    public void setWinnerId(Long winnerId) { this.winnerId = winnerId; }
    public Integer getMaxTeams() { return maxTeams; }
    public void setMaxTeams(Integer maxTeams) { this.maxTeams = maxTeams; }
    public Integer getNumberOfRounds() { return numberOfRounds; }
    public void setNumberOfRounds(Integer numberOfRounds) { this.numberOfRounds = numberOfRounds; }
}
