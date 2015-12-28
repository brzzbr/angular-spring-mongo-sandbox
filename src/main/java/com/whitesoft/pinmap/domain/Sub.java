package com.whitesoft.pinmap.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by borisbondarenko on 28.12.15.
 *
 * Domain entity for user's subscription
 *
 * @author brzzbr
 */
@Document(collection = "subscriptions")
public class Sub {

    @Id
    private String id;

    @DBRef
    @Indexed
    private User subscriber;

    @DBRef
    @Indexed
    private User author;

    private boolean active;

    private Date since;

    public String getId() {
        return id;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }
}
