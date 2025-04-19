package com.ecommerce.clientservice.domain.event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RealiseOrderEvent extends IEvent {
    private Long clientId;
    private Long orderId;
}
