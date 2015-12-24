package com.whitesoft.pinmap.dto;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.Date;

/**
 * Created by borisbondarenko on 23.12.15.
 *
 * DTO for a pin.
 *
 * @author brzzbr
 */
public class PinDTO {

    private String id;

    private String name;

    private String description;

    private String userName;

    private GeoJsonPoint location;

    private Date created;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUserName() {
        return userName;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public Date getCreated() {
        return created;
    }

    public PinDTO(String id, String name, String description, String userName, GeoJsonPoint location, Date created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userName = userName;
        this.location = location;
        this.created = created;
    }
}
