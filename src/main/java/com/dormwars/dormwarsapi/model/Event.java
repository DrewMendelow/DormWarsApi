package com.dormwars.dormwarsapi.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    private String location;
    private LocalDateTime datetime;

    @ManyToOne
    @JoinColumn(name = "sport_id", nullable = false)
    private Sport sport;

    private Boolean isTournamentGame = false;
    private String status;
    private String shortDescription;
    @Column(columnDefinition = "text")
    private String longDescription;

    @ManyToOne()
    @JoinColumn(name = "winner_id")
    private EventTeam winner;

    @ManyToOne()
    @JoinColumn(name = "loser_id")
    private EventTeam loser;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    private Integer roundNumber;

    // @ManyToOne
    // @JoinColumn(name = "next_event_id")
    private Long nextEventId;

    private String eventName;

    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public LocalDateTime getDatetime() { return datetime; }
    public void setDatetime(LocalDateTime datetime) { this.datetime = datetime; }
    public Sport getSport() { return sport; }
    public void setSport(Sport sport) { this.sport = sport; }
    public Boolean getIsTournamentGame() { return isTournamentGame; }
    public void setIsTournamentGame(Boolean isTournamentGame) { this.isTournamentGame = isTournamentGame; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }
    public String getLongDescription() { return longDescription; }
    public void setLongDescription(String longDescription) { this.longDescription = longDescription; }
    public EventTeam getWinner() { return winner; }
    public void setWinner(EventTeam winner) { this.winner = winner; }
    public EventTeam getLoser() { return loser; }
    public void setLoser(EventTeam loser) { this.loser = loser; }
    public Tournament getTournament() { return tournament; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }
    public Integer getRoundNumber() { return roundNumber; }
    public void setRoundNumber(Integer roundNumber) { this.roundNumber = roundNumber; }
    public Long getNextEventId() { return nextEventId; }
    public void setNextEventId(Long nextEventId) { this.nextEventId = nextEventId; }
    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }
}
