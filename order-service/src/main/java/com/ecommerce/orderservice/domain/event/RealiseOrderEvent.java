package com.ecommerce.orderservice.domain.event;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RealiseOrderEvent extends IEvent {
    private Long clientId;
    private Long orderId;
}
