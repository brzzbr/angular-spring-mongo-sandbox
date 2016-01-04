package com.whitesoft.pinmap.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by borisbondarenko on 30.12.15.
 *
 * I had an intention to use UNIX-timestamps as datetime-representation in the
 * project, why not? So here is the converter for it.
 *
 * @author brzzbr
 */
@Component
@MyConverter
public class UnixDateConverter implements Converter<String, Date>{

    @Override
    public Date convert(String s) {

        return new Date(Long.valueOf(s));
    }
}
