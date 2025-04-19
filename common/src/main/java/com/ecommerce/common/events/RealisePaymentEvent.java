package com.ecommerce.common.events;


import com.ecommerce.common.DTO.CreditCardDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RealisePaymentEvent extends IEvent {
    private Long orderId;
    private Long clientId;
    private CreditCardDTO creditCard;
}
