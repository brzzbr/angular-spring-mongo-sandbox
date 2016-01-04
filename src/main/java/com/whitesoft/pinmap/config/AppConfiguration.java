package com.whitesoft.pinmap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by borisbondarenko on 19.12.15.
 *
 * Root spring context. Stores contexts for mvcConfiguration,
 * persistence layer and security. Also initializes a placeholder
 * for a properties (for db properties for example).
 *
 * @author brzzbr
 */
@Configuration
@ComponentScan(basePackages = "com.whitesoft.pinmap")
@Import({ MvcConfiguration.class, RepositoryConfiguration.class, SecurityConfiguration.class })
public class AppConfiguration {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertyConfigIn() {

        return new PropertySourcesPlaceholderConfigurer();
    }
}
