package com.whitesoft.pinmap.dto;

/**
 * Created by borisbondarenko on 25.12.15.
 *
 * DTO class for retrieving a user information
 *
 * @author brzzbr
 */
public class UserDTO {

    private String firstName;

    private String lastName;

    private String fullName;

    private String email;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO() {
    }

    public UserDTO(String firstName, String lastName, String fullName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.email = email;
    }
}
