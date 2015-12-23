package com.whitesoft.pinmap.services;

import com.whitesoft.pinmap.domain.Pin;

import java.util.List;

/**
 * Created by borisbondarenko on 23.12.15.
 *
 * Interface for pins service
 *
 * @author brzzbr
 */
public interface PinService {

    /**
     * Gets pins of current authenticated user
     * @return collection of user's pin
     */
    List<Pin> getMyPins();
}
