package com.ecommerce.clientservice.domain.event;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class IEvent {

    @JsonIgnore
    public String getTopic() {
        return this.getClass().getSimpleName();
    }

}
