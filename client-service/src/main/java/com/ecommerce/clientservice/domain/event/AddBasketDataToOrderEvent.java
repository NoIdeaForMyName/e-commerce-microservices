package com.ecommerce.clientservice.domain.event;

import com.ecommerce.clientservice.domain.DTO.BasketDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddBasketDataToOrderEvent extends IEvent {
    private BasketDTO basket;
}
