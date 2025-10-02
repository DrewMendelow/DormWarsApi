package com.dormwars.dormwarsapi.model;

public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Long schoolId;
    private String userType;

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public Long getSchoolId() { return schoolId; }
    public void setSchoolId(Long schoolId) { this.schoolId = schoolId; }
    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }
}
