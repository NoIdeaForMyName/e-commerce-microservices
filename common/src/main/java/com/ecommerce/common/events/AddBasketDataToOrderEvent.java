package com.ecommerce.common.events;

import com.ecommerce.common.DTO.BasketDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddBasketDataToOrderEvent extends IEvent {
    private Long orderId;
    private BasketDTO basket;
}
