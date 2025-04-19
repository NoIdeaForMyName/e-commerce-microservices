package com.ecommerce.clientservice.domain.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ClientDTO {
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String address;
    //private final CreditCardEntity creditCard;
}
