package com.whitesoft.pinmap.controllers;

import com.whitesoft.pinmap.converters.SubDTOConverter;
import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.dto.CollectionDTO;
import com.whitesoft.pinmap.dto.SubDTO;
import com.whitesoft.pinmap.services.SubService;
import com.whitesoft.pinmap.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PathVariable;
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
    protected ConversionService conversionService;

    /**
     * Gets all user's subscriptions
     * @return subscriptions
     */
    @RequestMapping(
            value = "/subs",
            method = RequestMethod.GET
    )
    public CollectionDTO<SubDTO> getSubs(){

        User user = userService.getCurrentUser();
        return new CollectionDTO<>(subService.getSubs(user).stream()
                .map(sub -> conversionService.convert(sub, SubDTO.class))
                .collect(Collectors.toList()));
    }

    /**
     * Unsubscribes current user from author's pins
     * @param authorName aunthor name
     */
    @RequestMapping(
            value="/subs/{authorName}",
            method = RequestMethod.POST
    )
    public SubDTO subscribe(@PathVariable String authorName){

        User subscriber = userService.getCurrentUser();
        User author = userService.getUser(authorName);
        return conversionService.convert(subService.subscribe(subscriber, author), SubDTO.class);
    }

    /**
     * Unsubscribes current user from author's pins
     * @param authorName aunthor name
     */
    @RequestMapping(
            value="/subs/{authorName}",
            method = RequestMethod.DELETE
    )
    public void unsubscribe(@PathVariable  String authorName){

        User subscriber = userService.getCurrentUser();
        User author = userService.getUser(authorName);
        subService.unsubscribe(subscriber, author);
    }
}
