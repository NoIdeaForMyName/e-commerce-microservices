package com.ecommerce.clientservice.domain.event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateShipmentEvent extends IEvent {
    private Long orderId;
    private String address;
}
