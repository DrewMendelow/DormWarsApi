package com.dormwars.dormwarsapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tournaments")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tournamentId;

    @ManyToOne
    @JoinColumn(name = "sport_id", nullable = false)
    private Sport sport;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private EventTeam winner;

    private Integer maxTeams;
    private Integer numberOfRounds;

    public Long getTournamentId() { return tournamentId; }
    public void setTournamentId(Long tournamentId) { this.tournamentId = tournamentId; }
    public Sport getSport() { return sport; }
    public void setSport(Sport sport) { this.sport = sport; }
    public EventTeam getWinner() { return winner; }
    public void setWinner(EventTeam winner) { this.winner = winner; }
    public Integer getMaxTeams() { return maxTeams; }
    public void setMaxTeams(Integer maxTeams) { this.maxTeams = maxTeams; }
    public Integer getNumberOfRounds() { return numberOfRounds; }
    public void setNumberOfRounds(Integer numberOfRounds) { this.numberOfRounds = numberOfRounds; }
}
