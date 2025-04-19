package com.ecommerce.clientservice.domain.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    //private final CreditCardEntity creditCard;
}
