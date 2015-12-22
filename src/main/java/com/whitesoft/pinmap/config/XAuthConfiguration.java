package com.whitesoft.pinmap.config;

import com.whitesoft.pinmap.security.xauth.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* Configures x-auth-token security.
*/
@Configuration
public class XAuthConfiguration {

    @Bean
    public TokenProvider tokenProvider() {
        String secret = "myXAuthSecret";
        int validityInSeconds = 1800;
        return new TokenProvider(secret, validityInSeconds);
    }
}
