package com.ecommerce.clientservice.domain.event;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateShipmentEvent extends IEvent {
    private final Long orderId;
    private final String address;
}
