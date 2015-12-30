package com.whitesoft.pinmap.converters;

import com.whitesoft.pinmap.domain.Sub;
import com.whitesoft.pinmap.dto.SubDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by borisbondarenko on 29.12.15.
 *
 * Converter to convert domain Sub object to DTO representation.
 *
 * @author brzzbr
 */
@Component
@MyConverter
public class SubDTOConverter implements Converter<Sub, SubDTO> {

    @Override
    public SubDTO convert(Sub fromObj) {

        return new SubDTO(
                fromObj.getId(),
                fromObj.getAuthor().getUsername(),
                fromObj.getSubscriber().getUsername(),
                fromObj.getSince()
        );
    }
}
