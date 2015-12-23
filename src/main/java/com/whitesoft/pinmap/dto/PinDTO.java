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

    private String description;

    private GeoJsonPoint location;

    private Date created;

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public Date getCreated() {
        return created;
    }

    public PinDTO(String id, String description, GeoJsonPoint location, Date created) {
        this.id = id;
        this.description = description;
        this.location = location;
        this.created = created;
    }
}
