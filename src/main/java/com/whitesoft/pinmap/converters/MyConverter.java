package com.whitesoft.pinmap.converters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by borisbondarenko on 30.12.15.
 *
 * An annotation to mark my converters to be easely injected in {@link com.whitesoft.pinmap.config.MvcConfiguration}
 *
 * @author brzzbr
 */
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MyConverter {
}
