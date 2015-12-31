package com.whitesoft.pinmap.controllers;

import com.whitesoft.pinmap.domain.User;
import com.whitesoft.pinmap.dto.UserDTO;
import com.whitesoft.pinmap.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by borisbondarenko on 25.12.15.
 *
 * REST controller for working with users' information
 *
 * @author brzzbr
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    protected UserService userService;

    /**
     * Get user info by his login
     * @return username (login)
     */
    @RequestMapping(
            value = "/user/{username}",
            method = RequestMethod.GET
    )
    public UserDTO getUser(@PathVariable() String username){

        User user = userService.getUser(username);

        return new UserDTO(user.getId(),
                user.getUsername());
    }
}
