package com.ecommerce.clientservice.domain.event;


import com.ecommerce.clientservice.domain.DTO.CreditCardDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RealisePaymentEvent extends IEvent {
    private final Long orderId;
    private final Long clientId;
    private final CreditCardDTO creditCard;
}
