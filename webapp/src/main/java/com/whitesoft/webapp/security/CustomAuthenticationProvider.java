package com.whitesoft.webapp.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by borisbondarenko on 19.12.15.
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {



    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    public boolean supports(Class<?> authentication) {
        return false;
    }
}
