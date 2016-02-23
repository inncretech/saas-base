package com.inncretech.multitenancy.datasource.tenant.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Locale;

public class BasicUserDTO {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String firstName;

    private String lastName;

    private String middleName;

    private String pin;

    private String pathToImage;

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    @JsonIgnore
    public String getInitial() {
        String initial = "";
        if (firstName != null && firstName.length() > 0) {
            initial += firstName.charAt(0);
        }

        if (lastName != null && lastName.trim().length() > 0) {
            initial += lastName.charAt(0);
        }
        return initial.toUpperCase(Locale.US);
    }
}
