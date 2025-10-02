package com.dormwars.dormwarsapi.model;

import java.time.LocalDateTime;

public class EventRequest {
    private String location;
    private LocalDateTime datetime;
    private Long sportId;
    private Boolean isTournamentGame = false;
    private String status;
    private String shortDescription;
    private String longDescription;
    private Long winnerId;
    private Long loserId;
    private Long tournamentId;
    private Integer roundNumber;
    private Long nextEventId;
    private String eventName;

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public LocalDateTime getDatetime() { return datetime; }
    public void setDatetime(LocalDateTime datetime) { this.datetime = datetime; }
    public Long getSportId() { return sportId; }
    public void setSportId(Long sportId) { this.sportId = sportId; }
    public Boolean getIsTournamentGame() { return isTournamentGame; }
    public void setIsTournamentGame(Boolean isTournamentGame) { this.isTournamentGame = isTournamentGame; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }
    public String getLongDescription() { return longDescription; }
    public void setLongDescription(String longDescription) { this.longDescription = longDescription; }
    public Long getWinnerId() { return winnerId; }
    public void setWinnerId(Long winnerId) { this.winnerId = winnerId; }
    public Long getLoserId() { return loserId; }
    public void setLoser(Long loserId) { this.loserId = loserId; }
    public Long getTournamentId() { return tournamentId; }
    public void setTournamentId(Long tournamentId) { this.tournamentId = tournamentId; }
    public Integer getRoundNumber() { return roundNumber; }
    public void setRoundNumber(Integer roundNumber) { this.roundNumber = roundNumber; }
    public Long getNextEventId() { return nextEventId; }
    public void setNextEventId(Long nextEventId) { this.nextEventId = nextEventId; }
    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }
}

