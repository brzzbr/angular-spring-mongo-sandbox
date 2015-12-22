package com.whitesoft.pinmap.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by borisbondarenko on 19.12.15.
 *
 * A spring context for SpringMVC. Simply adds a resource handler for
 * static resources.
 *
 * @author brzzbr
 */
@Configuration
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
}
