package com.dormwars.dormwarsapi.model;

public class TeamRequest {
    private String teamName;
    private Long schoolId;
    private Boolean active;

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }
    public Long getSchoolId() { return schoolId; }
    public void setSchoolId(Long schoolId) { this.schoolId = schoolId; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
