package com.whitesoft.pinmap.services;

import com.whitesoft.pinmap.domain.Pin;
import com.whitesoft.pinmap.domain.User;

import java.util.List;

/**
 * Created by borisbondarenko on 23.12.15.
 *
 * Interface for pins service. Provides (in future) methods to work
 * with pins on map -- create, get pins for current user or
 * for subscription-users
 *
 * @author brzzbr
 */
public interface PinService {

    /**
     * Gets pins of current authenticated user including pins from subs
     * @return collection of user's pin
     */
    List<Pin> getPins(User user);

    /**
     * Submits new pin for current user
     * @param pin pin do add
     * @return returns posted pin
     */
    Pin addMyPin(User user, Pin pin);
}
