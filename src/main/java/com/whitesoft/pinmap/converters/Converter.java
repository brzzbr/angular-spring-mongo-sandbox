package com.whitesoft.pinmap.converters;

/**
 * Created by borisbondarenko on 27.12.15.
 *
 * Interface for a converter that is used to transfer domain entities to DTOs.
 *
 * @author brzzbr
 */
public interface Converter<From, To> {

    To convert(From fromObj);
}
