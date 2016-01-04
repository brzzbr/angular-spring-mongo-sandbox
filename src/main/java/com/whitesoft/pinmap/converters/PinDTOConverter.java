package com.whitesoft.pinmap.converters;

import com.whitesoft.pinmap.domain.Pin;
import com.whitesoft.pinmap.dto.PinDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by borisbondarenko on 27.12.15.
 * <p/>
 * Converter to convert domain Pin object to DTO representation.
 *
 * @author brzzbr
 */
@Component
@MyConverter
public class PinDTOConverter implements Converter<Pin, PinDTO> {

    @Override
    public PinDTO convert(Pin fromObj) {

        return new PinDTO(
                fromObj.getId(),
                fromObj.getName(),
                fromObj.getDescription(),
                fromObj.getUsername(),
                fromObj.getLocation(),
                fromObj.getCreated());
    }
}
