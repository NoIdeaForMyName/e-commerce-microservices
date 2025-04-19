package com.ecommerce.clientservice.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
public class ClientEntity {

    public ClientEntity() {}

    public ClientEntity(String firstname, String lastname, String email, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.creditCard = null;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Setter
    private String firstname;

    @NotNull
    @Setter
    private String lastname;

    @NotNull
    @Setter
    private String email;

    @NotNull
    @Setter
    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_card_id")
    @Setter
    private CreditCardEntity creditCard;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private List<ReservationEntity> reservations;
}
