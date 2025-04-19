package com.ecommerce.orderservice.domain.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
public class OrderEntity {

    public OrderEntity() {}

    public OrderEntity(Long fkClient) {
        this.fkClient = fkClient;
    }

    public OrderEntity(Long fkClient,Long fkPayment) {
        this.fkClient = fkClient;
        this.fkPayment = fkPayment;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    @NotNull
    private Long fkClient;

    @Setter
    private Long fkPayment;

    //@NotNull
    @CreationTimestamp
    private Instant createdOn;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderDetailEntity> orderDetails;
    
}
