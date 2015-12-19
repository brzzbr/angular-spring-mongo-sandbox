package com.whitesoft.webapp.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Created by borisbondarenko on 19.12.15.
 */
@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }
}
