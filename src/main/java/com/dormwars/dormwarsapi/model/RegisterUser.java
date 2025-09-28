package com.dormwars.dormwarsapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "register_users")
public class RegisterUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registerUserId;

    @ManyToOne
    @JoinColumn(name = "event_team_id", nullable = false)
    private EventTeam eventTeam;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Long getRegisterUserId() { return registerUserId; }
    public void setRegisterUserId(Long registerUserId) { this.registerUserId = registerUserId; }
    public EventTeam getEventTeam() { return eventTeam; }
    public void setEventTeam(EventTeam eventTeam) { this.eventTeam = eventTeam; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
