package com.ecommerce.shippingservice.infrastructure.eventhandler;


import com.ecommerce.common.events.CreateShipmentEvent;
import com.ecommerce.shippingservice.domain.DTO.ShipmentDTO;
import com.ecommerce.shippingservice.infrastructure.service.ShipmentCommandService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class EventHandler {

    private final ShipmentCommandService shipmentCommandService;

    private static final Logger logger = LogManager.getLogger(EventHandler.class);

    public EventHandler(ShipmentCommandService shipmentCommandService) {
        this.shipmentCommandService = shipmentCommandService;
    }

    @KafkaListener(topics = "CreateShipmentEvent", groupId = "shipping-group")
    @Transactional
    public void consume(CreateShipmentEvent createShipmentEvent) {
        logger.info("{} consumer received new message", createShipmentEvent.getTopic());
        shipmentCommandService.createShipment(new ShipmentDTO(createShipmentEvent.getOrderId(), createShipmentEvent.getAddress()));
        logger.info("Shipment for order: {} created", createShipmentEvent.getOrderId());
    }

}
