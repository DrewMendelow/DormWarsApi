package com.dormwars.dormwarsapi.model;

public class EventTeamRequest {
    private Long eventId;
    private Long teamId;
    private Boolean isTournamentTeam;
    private Long tournamentId;
    private Boolean active;

    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
    public Long getTeamId() { return teamId; }
    public void setTeamId(Long teamId) { this.teamId = teamId; }
    public Boolean getIsTournamentTeam() { return isTournamentTeam; }
    public void setIsTournamentTeam(Boolean isTournamentTeam) { this.isTournamentTeam = isTournamentTeam; }
    public Long getTournamentId() { return tournamentId; }
    public void setTournamentId(Long tournamentId) { this.tournamentId = tournamentId; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
