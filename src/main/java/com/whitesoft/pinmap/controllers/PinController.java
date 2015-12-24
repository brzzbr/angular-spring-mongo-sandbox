package com.whitesoft.pinmap.controllers;

import com.whitesoft.pinmap.dto.PinDTO;
import com.whitesoft.pinmap.dto.PinsCollectionDTO;
import com.whitesoft.pinmap.services.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * Gets pins for current authenticated user
     * @return collection of current user's pins
     */
    @RequestMapping(value = "/mypins",
            method = RequestMethod.GET
    )
    public PinsCollectionDTO getMyPins(){

        return new PinsCollectionDTO(pinService.getMyPins().stream()
                .map(pin -> new PinDTO(
                        pin.getId(),
                        pin.getName(),
                        pin.getDescription(),
                        "me",
                        pin.getLocation(),
                        pin.getCreated()))
                .collect(Collectors.toList()));
    }
}
