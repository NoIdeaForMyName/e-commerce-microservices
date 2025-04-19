package com.ecommerce.clientservice.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BasketDetailDTO {
    private Long productId;
    private Integer quantity;
}
