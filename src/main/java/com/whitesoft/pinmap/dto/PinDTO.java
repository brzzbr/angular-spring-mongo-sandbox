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

    private String username;

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

    public String getUsername() {
        return username;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public Date getCreated() {
        return created;
    }

    public PinDTO() {
    }

    public PinDTO(String id,
                  String name,
                  String description,
                  String username,
                  GeoJsonPoint location,
                  Date created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.username = username;
        this.location = location;
        this.created = created;
    }
}
