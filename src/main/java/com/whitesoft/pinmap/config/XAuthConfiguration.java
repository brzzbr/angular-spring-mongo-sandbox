package com.whitesoft.pinmap.config;

import com.whitesoft.pinmap.config.security.xauth.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by borisbondarenko on 19.12.15.
 *
 * Simple config for a {@link TokenProvider}. Properties are hardcoded yet.
 *
 * @author brzzbr
 */
@Configuration
public class XAuthConfiguration {

    @Bean
    public TokenProvider tokenProvider() {
        String secret = "myXAuthSecret";
        int validityInSeconds = 18000;
        return new TokenProvider(secret, validityInSeconds);
    }
}
