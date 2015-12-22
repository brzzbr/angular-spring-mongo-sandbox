package com.whitesoft.pinmap.controllers;

import com.whitesoft.pinmap.config.security.xauth.Token;
import com.whitesoft.pinmap.config.security.xauth.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

/**
 * Created by borisbondarenko on 18.12.15.
 *
 * REST Controller for authentication.
 *
 * @author brzzbr
 */
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    protected TokenProvider tokenProvider;

    @Autowired
    protected AuthenticationManager authenticationManager;

    @Autowired
    protected UserDetailsService userDetailsService;

    /**
     * Authentication method. Works on POST verb.
     * @param username username
     * @param password password
     * @return In case of success it returns {@link Token}
     */
    @RequestMapping(value = "/authenticate",
            method = RequestMethod.POST
    )
    public Token authorize(@RequestParam String username, @RequestParam String password) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = this.authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails details = this.userDetailsService.loadUserByUsername(username);
        return tokenProvider.createToken(details);
    }
}
