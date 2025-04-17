package com.ecommerce.orderservice.domain.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PlaceOrderDTO {
    @NotNull
    private final Long clientId;
}
