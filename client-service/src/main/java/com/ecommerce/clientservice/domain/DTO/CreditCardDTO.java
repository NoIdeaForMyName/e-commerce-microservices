package com.ecommerce.clientservice.domain.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class CreditCardDTO {
    private String number;
    private String CVV;
    private Date expirationDate;
}
