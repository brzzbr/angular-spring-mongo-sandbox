package com.whitesoft.pinmap.repositories;

import com.whitesoft.pinmap.domain.Pin;
import com.whitesoft.pinmap.domain.User;
import org.springframework.data.geo.Polygon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
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
     * Gets all pins for users list. It's used to retrieve
     * new pins from particular moment
     * @param users list of users
     * @return pins for list of users
     */
    List<Pin> findByUserInAndCreatedGreaterThan(List<User> users, Date fromDate);

    /**
     * Gets all the pins of user in particular area
     * @param user the user whom pins are going to be retrieved
     * @param polygon area to lookup
     * @return pins for user in the polygon
     */
    List<Pin> findByUserAndLocationWithin(User user, Polygon polygon);

    /**
     * Gets all pins for users list in particular area
     * @param users list of users whom pins are going to be retrieved
     * @param polygon area to lookup
     * @return pins for list of users in the polygon
     */
    List<Pin> findByUserInAndLocationWithin(List<User> users, Polygon polygon);

    /**
     * Deletes all the pins for user
     * @param user user whom pins are gonna be deleted
     * @return deleted pins3
     */
    List<Pin> deleteByUser(User user);
}
