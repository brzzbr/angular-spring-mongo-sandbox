package com.whitesoft.pinmap.controllers;

import com.whitesoft.pinmap.converters.PinToPinDTOConverter;
import com.whitesoft.pinmap.domain.Pin;
import com.whitesoft.pinmap.dto.PinDTO;
import com.whitesoft.pinmap.dto.PinsCollectionDTO;
import com.whitesoft.pinmap.services.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    protected PinService pinService;

    @Autowired
    protected PinToPinDTOConverter converter;

    /**
     * Gets pins for current authenticated user
     * @return collection of current user's pins with pins of subscription-users
     */
    @RequestMapping(
            value = "/mypins",
            method = RequestMethod.GET
    )
    public PinsCollectionDTO getPins(){

        return new PinsCollectionDTO(pinService.getMyPins().stream()
                .map(converter::convert)
                .collect(Collectors.toList()));
    }

    /**
     * Inserts new pin for current authenticated user
     * @param pin pin to insert
     * @return inserted pin
     */
    @RequestMapping(
            value = "/mypins",
            method = RequestMethod.POST,
            consumes = "application/json"
    )
    public PinDTO addPin(@RequestBody PinDTO pin){

        Pin pinToInsert = new Pin();
        pinToInsert.setName(pin.getName());
        pinToInsert.setDescription(pin.getDescription());
        pinToInsert.setLocation(pin.getLocation());
        pinToInsert.setCreated(new Date());

        Pin insertedPin = pinService.addMyPin(pinToInsert);
        return converter.convert(insertedPin);
    }
}
