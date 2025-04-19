package com.ecommerce.shippingservice.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Getter
public class ShipmentEntity {

    public ShipmentEntity() {}

    public ShipmentEntity(Long fkOrder, String address) {
        this.fkOrder = fkOrder;
        this.address = address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter
    @NotNull
    private Long fkOrder;

    @Setter
    @NotNull
    private String address;

    @CreationTimestamp
    private Instant createdOn;

}
