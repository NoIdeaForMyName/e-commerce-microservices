package com.ecommerce.orderservice.infrastructure.eventhandler;


import com.ecommerce.common.DTO.BasketDTO;
import com.ecommerce.common.DTO.BasketDetailDTO;
import com.ecommerce.common.events.AddBasketDataToOrderEvent;
import com.ecommerce.orderservice.domain.DTO.CreateOrderDetailDTO;
import com.ecommerce.orderservice.infrastructure.service.OrderCommandService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EventHandler {

    private final OrderCommandService orderCommandService;

    public EventHandler(OrderCommandService orderCommandService) {
        this.orderCommandService = orderCommandService;
    }

    @KafkaListener(topics = "AddBasketDataToOrderEvent", groupId = "order-group")
    @Transactional
    public void consume(AddBasketDataToOrderEvent addBasketDataToOrderEvent) {
        for (BasketDetailDTO baskedDetail : addBasketDataToOrderEvent.getBasket().getDetails())
            orderCommandService.addDetailToOrder(addBasketDataToOrderEvent.getOrderId(), new CreateOrderDetailDTO(baskedDetail.getProductId(), baskedDetail.getQuantity()));
    }

}
