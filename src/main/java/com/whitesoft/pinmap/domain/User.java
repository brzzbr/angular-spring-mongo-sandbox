package com.whitesoft.pinmap.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by borisbondarenko on 18.12.15.
 *
 * Domain entity for user.
 *
 * @author brzzbr
 */
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @Indexed
    private String username;
    private String password;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
