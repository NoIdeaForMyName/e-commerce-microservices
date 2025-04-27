package com.ecommerce.inventoryservice.infrastructure.eventhandler;


import com.ecommerce.common.events.RealisePaymentEvent;
import com.ecommerce.common.events.UpdateInventoryEvent;
import com.ecommerce.inventoryservice.infrastructure.service.InventoryCommandService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

@Component
public class EventHandler {

    private final InventoryCommandService inventoryCommandService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final Logger logger = LogManager.getLogger(EventHandler.class);

    public EventHandler(InventoryCommandService inventoryCommandService, KafkaTemplate<String, Object> kafkaTemplate) {
        this.inventoryCommandService = inventoryCommandService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "UpdateInventoryEvent", groupId = "inventory-group")
    @Transactional
    public void consume(UpdateInventoryEvent updateInventoryEvent) {
        logger.info("{} consumer received new message", updateInventoryEvent.getTopic());
        // update inventory
        inventoryCommandService.updateInventory(updateInventoryEvent.getBasket().getDetails());
        logger.info("Inventory data updated according to details assigned to order with id: {}", updateInventoryEvent.getOrderId());

        // sending event asking for payment processing
        BigDecimal totalAmount = inventoryCommandService.getTotalAmount(updateInventoryEvent.getBasket().getDetails());
        RealisePaymentEvent realisePaymentEvent = new RealisePaymentEvent(updateInventoryEvent.getOrderId(), updateInventoryEvent.getBasket().getClientId(), totalAmount);
        kafkaTemplate.send(realisePaymentEvent.getTopic(), realisePaymentEvent);
        logger.info("{} sent to other microservices", realisePaymentEvent.getTopic());
    }

}
