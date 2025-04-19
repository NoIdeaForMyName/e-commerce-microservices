package com.ecommerce.orderservice.domain.entity;


import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class OrderDetail_Id {

    public OrderDetail_Id() {}

    public OrderDetail_Id(Long fkClient, Long fkProduct) {
        this.fkOrder = fkClient;
        this.fkProduct = fkProduct;
    }

    @NotNull
    private Long fkOrder;

    @NotNull
    private Long fkProduct;

}
