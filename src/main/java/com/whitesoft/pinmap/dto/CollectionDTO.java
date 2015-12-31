package com.whitesoft.pinmap.dto;

import java.util.List;

/**
 * Created by borisbondarenko on 23.12.15.
 *
 * DTO for transfering generic collection.
 *
 * @author brzzbr
 */
public class CollectionDTO<T> {

    private List<T> items;

    public List<T> getItems() {
        return items;
    }

    public CollectionDTO(List<T> items) {
        this.items = items;
    }
}
