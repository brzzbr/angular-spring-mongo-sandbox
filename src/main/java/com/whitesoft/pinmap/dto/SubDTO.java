package com.whitesoft.pinmap.dto;

import java.util.Date;

/**
 * Created by borisbondarenko on 28.12.15.
 *
 * DTO for a subscription.
 *
 * @author brzzbr
 */
public class SubDTO {

    private String id;

    private String author;

    private String subscriber;

    private Date since;

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public Date getSince() {
        return since;
    }

    public SubDTO() {
    }

    public SubDTO(String id, String author, String subscriber, Date since) {
        this.id = id;
        this.author = author;
        this.subscriber = subscriber;
        this.since = since;
    }
}
