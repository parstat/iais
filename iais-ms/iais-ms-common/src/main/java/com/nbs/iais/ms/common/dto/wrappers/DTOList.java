package com.nbs.iais.ms.common.dto.wrappers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.nbs.iais.ms.common.dto.DTO;
import com.nbs.iais.ms.common.dto.Views;

import java.util.ArrayList;
import java.util.Arrays;

@JsonView(Views.Minimal.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DTOList<T extends DTO> extends ArrayList<T> implements DTO {

    public static final DTOList<DTO> EMPTY = new DTOList<>();
    private static final long serialVersionUID = 200L;

    public DTOList() {
    }

    public DTOList(final int amount) {
        super(amount);
    }

    public static <C extends DTO> DTOList<C> empty(final Class<C> clazz) {
        return new DTOList<>();
    }

    @SafeVarargs
    public static <D extends DTO> DTOList<D> create(final D... dto) {
        final DTOList<D> list = new DTOList<>();
        list.addAll(Arrays.asList(dto));
        return list;
    }


    public void join(final DTOList<T> dtoList) {
        if (dtoList != null && dtoList.size() > 0) {
            dtoList.forEach(d -> {
                if (!this.contains(d)) {
                    this.add(d);
                }
            });
        }
    }

}
