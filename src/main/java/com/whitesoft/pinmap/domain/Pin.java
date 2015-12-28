package com.whitesoft.pinmap.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

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

    private String name;

    private String description;

    @Indexed
    private GeoJsonPoint location;

    @Indexed
    private Date created;

    @DBRef
    @Indexed
    private User user;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setName(String name) {
        this.name = name;
    }
}
