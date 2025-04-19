package com.ecommerce.common.events;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddPaymentDataToOrderEvent extends IEvent {
    private Long orderId;
    private Long paymentId;
}
