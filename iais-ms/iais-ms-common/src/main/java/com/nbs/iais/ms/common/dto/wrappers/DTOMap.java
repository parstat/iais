package com.nbs.iais.ms.common.dto.wrappers;

import com.nbs.iais.ms.common.dto.DTO;

import java.util.HashMap;

public class DTOMap<T extends DTO> extends HashMap<String, T> implements DTO {

    public static final DTOMap<DTO> EMPTY = new DTOMap<>();

    private static final long serialVersionUID = 200L;

    public DTOMap() {
    }

    public DTOMap(final int amount) {
        super(amount);
    }

    public static <C extends DTO> DTOMap<C> empty(final Class<C> clazz) {
        return new DTOMap<>();
    }

}