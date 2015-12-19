package com.whitesoft.webapp.controllers;

import com.whitesoft.webapp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by borisbondarenko on 18.12.15.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    protected UserService userService;

}
