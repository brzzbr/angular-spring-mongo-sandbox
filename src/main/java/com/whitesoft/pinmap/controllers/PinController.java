package com.whitesoft.pinmap.controllers;

import com.whitesoft.pinmap.domain.Pin;
import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.dto.CollectionDTO;
import com.whitesoft.pinmap.dto.PinDTO;
import com.whitesoft.pinmap.services.PinService;
import com.whitesoft.pinmap.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by borisbondarenko on 23.12.15.
 *
 * REST controller for working with pins on the map
 *
 * @author brzzbr
 */
@RestController
@RequestMapping("/api")
public class PinController {

    @Autowired
    protected UserService userService;

    @Autowired
    protected PinService pinService;

    @Autowired
    protected ConversionService conversionService;

    private static Logger logger = LoggerFactory.getLogger(PinController.class);

    /**
     * Gets pins for current authenticated user
     * @return collection of current user's pins with pins of subscription-users
     */
    @RequestMapping(
            value = "/pins",
            method = RequestMethod.GET
    )
    public CollectionDTO<PinDTO> getPins(
            @RequestParam(value = "fromdate", required = false) Date fromDate){

        User user = userService.getCurrentUser();

        logger.debug("User {} gets his pins", user.getUsername());

        return new CollectionDTO<>(pinService.getPins(user, fromDate).stream()
                .map(pin -> conversionService.convert(pin, PinDTO.class))
                .collect(Collectors.toList()));
    }

    /**
     * Inserts new pin for current authenticated user
     * @param pin pin to insert
     * @return inserted pin
     */
    @RequestMapping(
            value = "/pins",
            method = RequestMethod.POST,
            consumes = "application/json"
    )
    public PinDTO addPin(@RequestBody PinDTO pin){

        Pin pinToInsert = new Pin();
        pinToInsert.setName(pin.getName());
        pinToInsert.setDescription(pin.getDescription());
        pinToInsert.setLocation(pin.getLocation());
        pinToInsert.setCreated(new Date());

        User user = userService.getCurrentUser();

        logger.debug("User {} adds pin {}", user.getUsername(), pin.getName());

        Pin insertedPin = pinService.addMyPin(user, pinToInsert);
        return conversionService.convert(insertedPin, PinDTO.class);
    }
}
