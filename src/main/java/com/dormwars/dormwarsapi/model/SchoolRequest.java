package com.dormwars.dormwarsapi.model;

public class SchoolRequest {
    private String schoolName;
    private String city;
    private String state;
    private String primaryColor;
    private String secondaryColor;
    private String schoolLogo;
    private Boolean active;

    public String getSchoolName() { return schoolName; }
    public void setSchoolName(String schoolName) { this.schoolName = schoolName; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getPrimaryColor() { return primaryColor; }
    public void setPrimaryColor(String primaryColor) { this.primaryColor = primaryColor; }
    public String getSecondaryColor() { return secondaryColor; }
    public void setSecondaryColor(String secondaryColor) { this.secondaryColor = secondaryColor; }
    public String getSchoolLogo() { return schoolLogo; }
    public void setSchoolLogo(String schoolLogo) { this.schoolLogo = schoolLogo; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
