package com.whitesoft.pinmap.repositories;

import com.whitesoft.pinmap.domain.Pin;
import com.whitesoft.pinmap.domain.User;
import org.springframework.data.geo.Polygon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by borisbondarenko on 22.12.15.
 *
 * Mongo repository for pins collection
 *
 * @author brzzbr
 */
public interface PinsRepository extends MongoRepository<Pin, String> {

    /**
     * Gets all the pins for particular user
     * @param user the user whom pins are going to be retrieved
     * @return pins for user
     */
    List<Pin> findByUser(User user);

    /**
     * Gets all pins for users list. It's used to retrieve
     * all the pins from ones subscriptions
     * @param users list of users
     * @return pins for list of users
     */
    List<Pin> findByUserIn(List<User> users);

    /**
     * Gets all the pins of user in particular area
     * @param user the user whom pins are going to be retrieved
     * @param polygon area to lookup
     * @return
     */
    List<Pin> findByUserAndLocationWithin(User user, Polygon polygon);

    /**
     * Gets all pins for users list in particular area
     * @param users list of users whom pins are going to be retrieved
     * @param polygon area to lookup
     * @return
     */
    List<Pin> findByUserInAndLocationWithin(List<User> users, Polygon polygon);
}
