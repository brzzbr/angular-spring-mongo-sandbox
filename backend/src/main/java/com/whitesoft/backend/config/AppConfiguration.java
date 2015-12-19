package com.whitesoft.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by borisbondarenko on 19.12.15.
 */
@Configuration
@ComponentScan(basePackages = "com.whitesoft.backend")
@Import({ MvcConfiguration.class, RepositoryConfiguration.class, SecurityConfiguration.class })
public class AppConfiguration {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertyConfigIn() {

        return new PropertySourcesPlaceholderConfigurer();
    }
}
