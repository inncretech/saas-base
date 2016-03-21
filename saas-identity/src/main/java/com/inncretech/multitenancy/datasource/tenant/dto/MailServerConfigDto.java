package com.inncretech.multitenancy.datasource.tenant.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.appanalytixs.dto.AbstractDto;

public class MailServerConfigDto extends AbstractDto {

    private Long mailServerConfigId;

    @NotBlank(message = "Mail Server Name Can't Be Blank")
    private String name;
    @NotBlank(message = "Mail Server Host Can't Be Blank")
    private String host;

    @NotNull(message = "Mail Server Port Can't Be Blank")
    @Min(value = 1, message = "Mail Server Port Can't Be 0")
    private Integer port;

    @NotNull(message = "Mail Server Sender Can't Be Blank")
    private String sender;

    @Email(message = "Mail Server Email Pattern is Not Correct")
    @NotBlank(message = "Mail Server Email Can't Be Blank")
    private String email;

    private boolean primary;
    private boolean active;

    public Long getMailServerConfigId() {
        return mailServerConfigId;
    }

    public void setMailServerConfigId(Long mailServerConfigId) {
        this.mailServerConfigId = mailServerConfigId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
