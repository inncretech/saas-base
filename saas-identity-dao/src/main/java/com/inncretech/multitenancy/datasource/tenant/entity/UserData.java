package com.inncretech.multitenancy.datasource.tenant.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import com.inncretech.multitenancy.datasource.dto.TenantAware;
import com.inncretech.multitenancy.datasource.master.entity.AbstractPersistentObject;

@Entity
@Table(name = "user_data")
@DynamicInsert
@DynamicUpdate
public class UserData extends AbstractPersistentObject implements TenantAware {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "tenant_id")
    private Long tenantId;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "salt")
    private String salt;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "active", columnDefinition = " TINYINT(1) default 0 ", length = 1)
    private boolean active;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "profile_pic")
    private String profilePic;

    @Column(name = "failed_logins")
    private int failedLogins;

    @Column(name = "last_failed_login_date")
    private Date lastFailedLoginDate;

    @Column(name = "last_login_date")
    private Date lastLoginDate;
    
    @Column(name = "password_salt")
    private String passwordSalt;

    @Column(name = "master_key")
    private String masterKey;
    
    
    /*
     * @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
     * 
     * @JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns =
     * { @JoinColumn(name = "role_id", referencedColumnName = "id") }) List<Role> roles;
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }
    
    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getMasterKey() {
        return masterKey;
    }

    public void setMasterKey(String masterKey) {
        this.masterKey = masterKey;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserData [id=").append(id).append(", tenantId=").append(tenantId).append(", userName=").append(userName).append(", password=")
                .append(password).append(", salt=").append(salt).append(", email=").append(email).append(", firstName=").append(firstName)
                .append(", lastName=").append(lastName).append(", active=").append(active).append(", phoneNumber=").append(phoneNumber)
                .append(", profilePic=").append(profilePic).append(", failedLogins=").append(failedLogins).append(", lastFailedLoginDate=")
                .append(lastFailedLoginDate).append(", lastLoginDate=").append(lastLoginDate).append(", passwordSalt=").append(passwordSalt)
                .append(", masterKey=").append(masterKey).append(", toString()=").append(super.toString()).append("]");
        return builder.toString();
    }
}
