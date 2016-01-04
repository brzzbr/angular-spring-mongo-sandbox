package com.whitesoft.pinmap.dto;

/**
 * Created by borisbondarenko on 25.12.15.
 *
 * DTO class for retrieving a user information
 *
 * @author brzzbr
 */
public class UserDTO {

    private String id;

    private String username;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public UserDTO() {
    }

    public UserDTO(String id, String username) {
        this.id = id;
        this.username = username;
    }
}
