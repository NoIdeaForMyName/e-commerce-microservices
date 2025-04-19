package com.ecommerce.clientservice.domain.event;

import com.ecommerce.clientservice.domain.DTO.BasketDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public class UpdateInventoryEvent extends IEvent {
    private final BasketDTO basket;
}
