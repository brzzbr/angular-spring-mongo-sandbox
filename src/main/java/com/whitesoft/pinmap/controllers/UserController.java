package com.whitesoft.pinmap.controllers;

import com.whitesoft.pinmap.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;

/**
 * Created by borisbondarenko on 18.12.15.
 */
@RestController
public class UserController {

    @Autowired
    protected UserService userService;

    @RequestMapping("/api/user")
    public Principal getCurrentUser(Principal user){
        return user;
    }
}
