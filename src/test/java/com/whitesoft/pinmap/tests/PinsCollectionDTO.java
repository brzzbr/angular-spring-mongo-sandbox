package com.whitesoft.pinmap.tests;

import com.whitesoft.pinmap.dto.CollectionDTO;
import com.whitesoft.pinmap.dto.PinDTO;

import java.util.List;

/**
 * Created by borisbondarenko on 03.01.16.
 */
public class PinsCollectionDTO extends CollectionDTO<PinDTO> {

    public PinsCollectionDTO(List<PinDTO> items) {
        super(items);
    }
}
