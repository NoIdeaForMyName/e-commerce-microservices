package com.ecommerce.common.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class BasketDTO {

    public BasketDTO(Long clientId) {
        this.clientId = clientId;
        this.details = new ArrayList<>();
    }

    private final Long clientId;
    private final List<BasketDetailDTO> details;

    public boolean isEmpty() {
        return this.details.isEmpty();
    }

}
