package com.whitesoft.webapp.config;

import com.whitesoft.webapp.security.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by borisbondarenko on 19.12.15.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.whitesoft.webapp")
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Bean
    public UserDetailsService getUserDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertyConfigIn() {

        return new PropertySourcesPlaceholderConfigurer();
    }
}
