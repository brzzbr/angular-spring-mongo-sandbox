package com.whitesoft.pinmap.services;

import com.whitesoft.pinmap.domain.Pin;
import com.whitesoft.pinmap.domain.Sub;
import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.repositories.PinsRepository;
import com.whitesoft.pinmap.services.exceptions.InvalidPinException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by borisbondarenko on 23.12.15.
 * <p/>
 * Implementation of pins service
 *
 * @author brzzbr
 */
@Service
public class PinServiceImpl implements PinService {

    @Autowired
    protected SubService subService;

    @Autowired
    protected PinsRepository pinsRepository;

    @Override
    public List<Pin> getPins(User user) {

        return getPins(user, null);
    }

    @Override
    public List<Pin> getPins(User user, Date fromDate) {

        // gets all subscription authors
        List<User> pinsAuthors = subService.getSubs(user).stream()
                .map(Sub::getAuthor)
                .collect(Collectors.toList());

        // let's add current user in the list to retrieve pins in one request
        pinsAuthors.add(user);

        List<Pin> result;
        if (fromDate == null)
            result = pinsRepository.findByUserIn(pinsAuthors);
        else
            result = pinsRepository.findByUserInAndCreatedGreaterThan(pinsAuthors, fromDate);

        return result;
    }

    @Override
    public Pin addMyPin(User user, Pin pin) {

        if (pin.getLocation() == null ||
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
