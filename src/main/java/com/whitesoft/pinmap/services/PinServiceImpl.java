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
    protected PinsRepository pinsRepository;

    @Override
    public List<Pin> getMyPins() {

        User currentUser = userService.getCurrentUser();
        return pinsRepository.findByUser(currentUser);
    }

    @Override
    public Pin addMyPin(Pin pin) {

        if(pin.getLocation() == null ||
                pin.getLocation().getX() > 180.0 ||
                pin.getLocation().getX() < -180.0 ||
                pin.getLocation().getY() > 90.0 ||
                pin.getLocation().getY() < -90.0) {
            throw new InvalidPinException();
        }

        User currentUser = userService.getCurrentUser();
        pin.setUser(currentUser);

        return pinsRepository.insert(pin);
    }
}
