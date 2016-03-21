package com.inncretech.multitenancy.datasource.tenant.dto;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.appanalytixs.dto.AbstractDto;

public class UserDto extends AbstractDto {

    private Long userId;

    private String userName;

    private String password;

    private String salt;

    private String email;

    private String firstName;

    private String lastName;

    private boolean active;

    private String phoneNumber;

    private String profilePic;

    private int failedLogins;

    private Date lastFailedLoginDate;

    private Date lastLoginDate;

    @Valid
    private List<RoleDto> roles;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public int getFailedLogins() {
        return failedLogins;
    }

    public void setFailedLogins(int failedLogins) {
        this.failedLogins = failedLogins;
    }

    public Date getLastFailedLoginDate() {
        return lastFailedLoginDate;
    }

    public void setLastFailedLoginDate(Date lastFailedLoginDate) {
        this.lastFailedLoginDate = lastFailedLoginDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserDto [userId=").append(userId).append(", userName=").append(userName).append(", password=").append(password)
                .append(", salt=").append(salt).append(", email=").append(email).append(", firstName=").append(firstName).append(", lastName=")
                .append(lastName).append(", active=").append(active).append(", phoneNumber=").append(phoneNumber).append(", profilePic=")
                .append(profilePic).append(", failedLogins=").append(failedLogins).append(", lastFailedLoginDate=").append(lastFailedLoginDate)
                .append(", lastLoginDate=").append(lastLoginDate).append(", roles=").append(roles).append("]");
        return builder.toString();
    }
}
