package com.ecommerce.common.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditCardDTO {
    private String number;
    private String cvv;
    private Date expirationDate;
}
