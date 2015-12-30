package com.whitesoft.pinmap.converters;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by borisbondarenko on 30.12.15.
 */
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface MyConverter {
}
