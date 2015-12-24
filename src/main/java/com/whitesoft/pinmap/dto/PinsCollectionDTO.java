package com.whitesoft.pinmap.dto;

import java.util.List;

/**
 * Created by borisbondarenko on 23.12.15.
 *
 * DTO for transfering collection of pins.
 *
 * @author brzzbr
 */
public class PinsCollectionDTO {

    private List<PinDTO> items;

    public List<PinDTO> getItems() {
        return items;
    }

    public PinsCollectionDTO(List<PinDTO> items) {
        this.items = items;
    }
}
