package com.ecommerce.clientservice.domain.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateReservationDTO {
    private Long productFk;
    private Integer quantity;
}
