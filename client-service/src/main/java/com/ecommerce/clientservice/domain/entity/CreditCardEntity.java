package com.ecommerce.clientservice.domain.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
public class CreditCardEntity {

    public CreditCardEntity() {}

    public CreditCardEntity(String number, String CVV, Date expirationDate) {
        this.number = number;
        this.CVV = CVV;
        this.expirationDate = expirationDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Setter
    private String number;

    @NotNull
    @Setter
    private String CVV;

    @NotNull
    @Setter
    private Date expirationDate;

    @OneToOne(mappedBy = "creditCard")
    private ClientEntity client;
}
