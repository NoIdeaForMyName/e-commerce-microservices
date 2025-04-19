package com.ecommerce.orderservice.infrastructure.service;

import com.ecommerce.orderservice.domain.DTO.PlaceOrderDTO;
import com.ecommerce.orderservice.domain.entity.OrderEntity;
import com.ecommerce.common.events.RealiseOrderEvent;
import com.ecommerce.orderservice.domain.repository.OrderRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderCommandService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderCommandService(OrderRepository orderRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void placeOrder(PlaceOrderDTO placeOrderDTO) {
        // save to database
        OrderEntity order = new OrderEntity(placeOrderDTO.getClientId());
        orderRepository.save(order);

        // send event to client microservice
        RealiseOrderEvent realiseOrderEvent = new RealiseOrderEvent(placeOrderDTO.getClientId(), order.getId());
        kafkaTemplate.send(realiseOrderEvent.getTopic(), realiseOrderEvent);
    }

}
