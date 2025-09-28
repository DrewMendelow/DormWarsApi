package com.dormwars.dormwarsapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "event_teams")
public class EventTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventTeamId;

    // @ManyToOne
    // @JoinColumn(name = "event_id", nullable = false)
    // private Event event;

    private Long eventId;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    private Boolean isTournamentTeam = false;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    private Boolean active = true;

    public Long getEventTeamId() { return eventTeamId; }
    public void setEventTeamId(Long eventTeamId) { this.eventTeamId = eventTeamId; }
    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }
    public Boolean getIsTournamentTeam() { return isTournamentTeam; }
    public void setIsTournamentTeam(Boolean isTournamentTeam) { this.isTournamentTeam = isTournamentTeam; }
    public Tournament getTournament() { return tournament; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
