package com.ecommerce.inventoryservice.infrastructure.eventhandler;


import com.ecommerce.common.events.RealisePaymentEvent;
import com.ecommerce.common.events.UpdateInventoryEvent;
import com.ecommerce.inventoryservice.infrastructure.service.InventoryCommandService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class EventHandler {

    private final InventoryCommandService inventoryCommandService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public EventHandler(InventoryCommandService inventoryCommandService, KafkaTemplate<String, Object> kafkaTemplate) {
        this.inventoryCommandService = inventoryCommandService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "UpdateInventoryEvent", groupId = "inventory-group")
    @Transactional
    public void consume(UpdateInventoryEvent updateInventoryEvent) {
        // update inventory
        inventoryCommandService.updateInventory(updateInventoryEvent.getBasket().getDetails());

        // sending event asking for payment processing
        BigDecimal totalAmount = inventoryCommandService.getTotalAmount(updateInventoryEvent.getBasket().getDetails());
        RealisePaymentEvent realisePaymentEvent = new RealisePaymentEvent(updateInventoryEvent.getOrderId(), updateInventoryEvent.getBasket().getClientId(), totalAmount);
        kafkaTemplate.send(realisePaymentEvent.getTopic(), realisePaymentEvent);
    }

}
