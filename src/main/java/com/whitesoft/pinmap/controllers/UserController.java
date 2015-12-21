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

    @RequestMapping("/user")
    public Principal getCurrentUser(Principal user){
        return user;
    }

    @RequestMapping("/token")
    @ResponseBody
    public Map<String,String> token(HttpSession session) {
        return Collections.singletonMap("token", session.getId());
    }
}
