package com.ecommerce.clientservice.domain.event;


import com.ecommerce.clientservice.domain.DTO.CreditCardDTO;
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
