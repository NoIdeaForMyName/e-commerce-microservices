package com.ecommerce.clientservice.domain.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class CreateReservationDTO {
    private final Long productFk;
    private final Integer quantity;
}
