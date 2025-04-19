package com.ecommerce.clientservice.domain.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class ReservationId implements Serializable {

    public ReservationId() {}

    public ReservationId(Long fkClient, Long fkProduct) {
        this.fkClient = fkClient;
        this.fkProduct = fkProduct;
    }

    @NotNull
    private Long fkClient;

    @NotNull
    private Long fkProduct;

}
