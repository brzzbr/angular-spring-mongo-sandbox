package com.whitesoft.pinmap.controllers;

import com.whitesoft.pinmap.converters.SubDTOConverter;
import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.dto.CollectionDTO;
import com.whitesoft.pinmap.dto.SubDTO;
import com.whitesoft.pinmap.services.SubService;
import com.whitesoft.pinmap.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * Created by borisbondarenko on 28.12.15.
 *
 * REST controller for working with users' subscriptions on the other users' pins.
 *
 * @author brzzbr
 */
@RestController
@RequestMapping("/api")
public class SubController {

    @Autowired
    protected UserService userService;

    @Autowired
    protected SubService subService;

    @Autowired
    protected SubDTOConverter converter;

    @RequestMapping(
            value = "/subs",
            method = RequestMethod.GET
    )
    public CollectionDTO<SubDTO> getSubs(){

        User user = userService.getCurrentUser();
        return new CollectionDTO<>(subService.getSubs(user).stream()
                .map(converter::convert)
                .collect(Collectors.toList()));
    }
}
