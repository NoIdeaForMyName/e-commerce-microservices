package com.ecommerce.common.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BasketDetailDTO {
    private Long productId;
    private Integer quantity;
}
