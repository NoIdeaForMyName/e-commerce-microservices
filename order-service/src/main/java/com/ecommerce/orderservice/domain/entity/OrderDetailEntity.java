package com.ecommerce.orderservice.domain.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class OrderDetailEntity {

    public OrderDetailEntity() {}

    public OrderDetailEntity(OrderEntity order, Long fkProduct, Integer quantity) {
        this.id = new OrderDetail_Id(order.getId(), fkProduct);
        this.order = order;
        this.quantity = quantity;
    }

    @EmbeddedId
    private OrderDetail_Id id;

    @Setter
    @NotNull
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("fkOrder")
    @JoinColumn(name = "fk_order")
    private OrderEntity order;

}
