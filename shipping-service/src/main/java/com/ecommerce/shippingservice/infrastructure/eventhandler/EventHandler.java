package com.ecommerce.shippingservice.infrastructure.eventhandler;


import com.ecommerce.common.events.CreateShipmentEvent;
import com.ecommerce.shippingservice.domain.DTO.ShipmentDTO;
import com.ecommerce.shippingservice.infrastructure.service.ShipmentCommandService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EventHandler {

    private final ShipmentCommandService shipmentCommandService;

    public EventHandler(ShipmentCommandService shipmentCommandService) {
        this.shipmentCommandService = shipmentCommandService;
    }

    @KafkaListener(topics = "CreateShipmentEvent", groupId = "shipping-group")
    @Transactional
    public void consume(CreateShipmentEvent createShipmentEvent) {
        shipmentCommandService.createShipment(new ShipmentDTO(createShipmentEvent.getOrderId(), createShipmentEvent.getAddress()));
    }

}
