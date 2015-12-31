package com.whitesoft.pinmap.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by borisbondarenko on 30.12.15.
 */
@Component
@MyConverter
public class UnixDateConverter implements Converter<String, Date>{

    @Override
    public Date convert(String s) {

        return new Date(Long.valueOf(s));
    }
}
