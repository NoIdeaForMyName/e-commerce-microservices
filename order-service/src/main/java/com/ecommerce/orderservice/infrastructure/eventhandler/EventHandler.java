package com.ecommerce.orderservice.infrastructure.eventhandler;


import com.ecommerce.common.DTO.BasketDTO;
import com.ecommerce.common.DTO.BasketDetailDTO;
import com.ecommerce.common.events.AddBasketDataToOrderEvent;
import com.ecommerce.common.events.AddPaymentDataToOrderEvent;
import com.ecommerce.orderservice.domain.DTO.CreateOrderDetailDTO;
import com.ecommerce.orderservice.infrastructure.service.OrderCommandService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class EventHandler {

    private final OrderCommandService orderCommandService;

    private static final Logger logger = LogManager.getLogger(EventHandler.class);

    public EventHandler(OrderCommandService orderCommandService) {
        this.orderCommandService = orderCommandService;
    }

    @KafkaListener(topics = "AddBasketDataToOrderEvent", groupId = "order-group")
    @Transactional
    public void consume(AddBasketDataToOrderEvent addBasketDataToOrderEvent) {
        logger.info("{} consumer received new message", addBasketDataToOrderEvent.getTopic());
        for (BasketDetailDTO baskedDetail : addBasketDataToOrderEvent.getBasket().getDetails())
            orderCommandService.addDetailToOrder(addBasketDataToOrderEvent.getOrderId(), new CreateOrderDetailDTO(baskedDetail.getProductId(), baskedDetail.getQuantity()));
        logger.info("All basket details assigned to order: {}", addBasketDataToOrderEvent.getOrderId());
    }

    @KafkaListener(topics = "AddPaymentDataToOrderEvent", groupId = "order-group")
    @Transactional
    public void consume(AddPaymentDataToOrderEvent addPaymentDataToOrderEvent) {
        logger.info("{} consumer received new message", addPaymentDataToOrderEvent.getTopic());
        orderCommandService.addPaymentToOrder(addPaymentDataToOrderEvent.getOrderId(), addPaymentDataToOrderEvent.getPaymentId());
        logger.info("Payment data with id: {} added to order with id: {}", addPaymentDataToOrderEvent.getPaymentId(), addPaymentDataToOrderEvent.getOrderId());
    }

}
