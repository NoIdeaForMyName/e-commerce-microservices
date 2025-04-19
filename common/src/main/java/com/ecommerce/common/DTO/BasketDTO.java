package com.ecommerce.common.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BasketDTO {

    public BasketDTO(Long clientId) {
        this.clientId = clientId;
        this.details = new ArrayList<>();
    }

    private Long clientId;
    private List<BasketDetailDTO> details;

    @JsonIgnore
    public boolean isEmpty() {
        return this.details.isEmpty();
    }

}
