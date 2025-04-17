package com.ecommerce.orderservice.domain.event;

public abstract class IEvent {

    public String getTopic() {
        return this.getClass().getName();
    }

}
