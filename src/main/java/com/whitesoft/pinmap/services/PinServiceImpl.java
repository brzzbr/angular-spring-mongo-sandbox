package com.whitesoft.pinmap.services;

import com.whitesoft.pinmap.domain.Pin;
import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.repositories.PinsRepository;
import com.whitesoft.pinmap.services.exceptions.InvalidPinException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by borisbondarenko on 23.12.15.
 *
 * Implementation of pins service
 *
 * @author brzzbr
 */
@Service
public class PinServiceImpl implements PinService {

    @Autowired
    protected UserService userService;

    @Autowired
    protected SubService subService;

    @Autowired
    protected PinsRepository pinsRepository;

    @Override
    public List<Pin> getPins(User user) {

        return pinsRepository.findByUser(user);
    }

    @Override
    public Pin addMyPin(User user, Pin pin) {

        if(pin.getLocation() == null ||
                pin.getLocation().getX() > 180.0 ||
                pin.getLocation().getX() < -180.0 ||
                pin.getLocation().getY() > 90.0 ||
                pin.getLocation().getY() < -90.0) {
            throw new InvalidPinException();
        }

        pin.setUser(user);
        pin.setUsername(user.getUsername());

        return pinsRepository.insert(pin);
    }
}
