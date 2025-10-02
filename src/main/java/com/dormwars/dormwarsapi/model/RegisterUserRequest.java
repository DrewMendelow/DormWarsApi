package com.dormwars.dormwarsapi.model;

public class RegisterUserRequest {
    private Long eventTeamId;
    private Long userId;

    public Long getEventTeamId() { return eventTeamId; }
    public void setEventTeamId(Long eventTeamId) { this.eventTeamId = eventTeamId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
