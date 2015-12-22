package com.whitesoft.pinmap.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by borisbondarenko on 22.12.15.
 *
 * Domain entity for pin on the map.
 *
 * @author brzzbr
 */
@Document(collection = "pins")
public class Pin {

    @Id
    private String id;

    private String description;

    private GeoJsonPoint location;

    @DBRef
    @Indexed
    private User user;

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
