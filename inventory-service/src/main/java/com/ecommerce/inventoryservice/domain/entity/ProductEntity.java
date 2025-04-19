package com.ecommerce.inventoryservice.domain.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Getter
public class ProductEntity {

    public ProductEntity() {}

    public ProductEntity(String name, BigDecimal amount, Integer quantity) {
        this.name = name;
        this.amount = amount;
        this.quantity = quantity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    @NotNull
    private String name;

    @Setter
    @NotNull
    private BigDecimal amount;

    @Setter
    @NotNull
    private Integer quantity;
    
}
