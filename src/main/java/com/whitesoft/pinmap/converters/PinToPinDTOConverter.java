package com.whitesoft.pinmap.converters;

import com.whitesoft.pinmap.domain.Pin;
import com.whitesoft.pinmap.dto.PinDTO;
import org.springframework.stereotype.Service;

/**
 * Created by borisbondarenko on 27.12.15.
 * <p/>
 * Converter to convert domain Pin object to DTO representation.
 *
 * @author brzzbr
 */
@Service
public class PinToPinDTOConverter implements Converter<Pin, PinDTO> {

    @Override
    public PinDTO convert(Pin fromObj) {

        return new PinDTO(
                fromObj.getId(),
                fromObj.getName(),
                fromObj.getDescription(),
                String.format("%s %s",
                        fromObj.getUser().getFirstName(),
                        fromObj.getUser().getLastName()),
                fromObj.getLocation(),
                fromObj.getCreated());
    }
}
