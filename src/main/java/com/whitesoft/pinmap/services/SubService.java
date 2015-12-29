package com.whitesoft.pinmap.services;

import com.whitesoft.pinmap.domain.Sub;
import com.whitesoft.pinmap.domain.User;

import java.util.List;

/**
 * Created by borisbondarenko on 28.12.15.
 *
 * Interface for subscription service. Allows to get subscriptions,
 * subscribe or unsubscribe on users' pins.
 *
 * @author brzzbr
 */
public interface SubService {

    /**
     * Gets all subscriptions for current user
     * @return a list of user"s subscriptions
     */
    List<Sub> getSubs(User user);

    /**
     * Subscribes subscriber to author's pins
     * @param subscriber subscriber user
     * @param author author user
     */
    Sub subscribe(User subscriber, User author);

    /**
     * Unsubscribes subscriber from author's pins
     * @param subscriber subscriber user
     * @param author author user
     */
    void unsubscribe(User subscriber, User author);
}
