package com.ecommerce.paymentservice.domain.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
public class PaymentEntity {

    public PaymentEntity() {}

    public PaymentEntity(Long fkClient, Long fkOrder, BigDecimal amount) {
        this.fkClient = fkClient;
        this.fkOrder = fkOrder;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    @NotNull
    private Long fkClient;

    @Setter
    @NotNull
    private Long fkOrder;

    @Setter
    @NotNull
    private BigDecimal amount;

    @CreationTimestamp
    private Instant createdOn;
    
}
