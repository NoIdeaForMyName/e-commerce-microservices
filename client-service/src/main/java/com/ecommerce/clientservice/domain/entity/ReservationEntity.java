package com.ecommerce.clientservice.domain.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Getter
public class ReservationEntity {

    public ReservationEntity() {}

    public ReservationEntity(ClientEntity client, Long fkProduct, Integer quantity) {
        this.id = new ReservationId(client.getId(), fkProduct);
        this.client = client;
        this.quantity = quantity;
    }

    @EmbeddedId
    private ReservationId id;

    @Setter
    @NotNull
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("fkClient")
    @JoinColumn(name = "fk_client")
    private ClientEntity client;
}


//@Embeddable
//@Getter
//@Setter
//@EqualsAndHashCode
//class ReservationId implements Serializable {
////
////    public ReservationId() {}
////
////    public ReservationId(Long fkClient, Long fkProduct) {
////        this.fkClient = fkClient;
////        this.fkProduct = fkProduct;
////    }
//
//    @NotNull
//    private Long fkClient;
//
//    @NotNull
//    private Long fkProduct;
//
//}
