package com.whitesoft.pinmap.repositories;

import com.whitesoft.pinmap.domain.Sub;
import com.whitesoft.pinmap.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by borisbondarenko on 28.12.15.
 *
 * Mongo repository for users' subscriptions
 *
 * @author brzzbr
 */
public interface SubsRepository extends MongoRepository<Sub, String>{

    /**
     * Gets all subscribes for some user
     * @param subscriber user
     * @return collection of subscribes
     */
    List<Sub> findBySubscriber(User subscriber);

    /**
     * Gets all subscribes of author for some subscriber
     * @param subscriber subscriber
     * @param author author
     * @return subscription
     */
    Sub findBySubscriberAndAuthor(User subscriber, User author);
}
