package com.whitesoft.pinmap.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.whitesoft.pinmap.config.serialization.GSONFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

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

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(createGsonHttpMessageConverter());
        super.configureMessageConverters(converters);
    }

    private GsonHttpMessageConverter createGsonHttpMessageConverter() {
        GsonHttpMessageConverter gsonConverter = new GsonHttpMessageConverter();
        gsonConverter.setGson(GSONFactory.getGson());

        return gsonConverter;
    }
}
