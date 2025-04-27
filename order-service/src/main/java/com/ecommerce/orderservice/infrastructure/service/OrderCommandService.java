package com.ecommerce.orderservice.infrastructure.service;

import com.ecommerce.orderservice.domain.DTO.CreateOrderDetailDTO;
import com.ecommerce.orderservice.domain.DTO.PlaceOrderDTO;
import com.ecommerce.orderservice.domain.entity.OrderDetailEntity;
import com.ecommerce.orderservice.domain.entity.OrderEntity;
import com.ecommerce.common.events.RealiseOrderEvent;
import com.ecommerce.orderservice.domain.repository.OrderDetailRepository;
import com.ecommerce.orderservice.domain.repository.OrderRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class OrderCommandService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final Logger logger = LogManager.getLogger(OrderCommandService.class);

    public OrderCommandService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void placeOrder(PlaceOrderDTO placeOrderDTO) {
        logger.info("Placing order for client with id: {} started", placeOrderDTO.getClientId());

        // save to database
        OrderEntity order = new OrderEntity(placeOrderDTO.getClientId());
        orderRepository.save(order);
        logger.info("Empty order record saved into database with id: {}", order.getId());

        // send event to client microservice
        RealiseOrderEvent realiseOrderEvent = new RealiseOrderEvent(placeOrderDTO.getClientId(), order.getId());
        kafkaTemplate.send(realiseOrderEvent.getTopic(), realiseOrderEvent);
        logger.info("{} sent to other microservices", realiseOrderEvent.getTopic());
    }

    public void addDetailToOrder(Long orderId, CreateOrderDetailDTO createOrderDetailDTO) {
        OrderEntity order = orderRepository.findById(orderId).orElseThrow();
        OrderDetailEntity newOrderDetail = new OrderDetailEntity(order, createOrderDetailDTO.getProductId(), createOrderDetailDTO.getQuantity());
        orderDetailRepository.save(newOrderDetail);
    }

    public void addPaymentToOrder(Long orderId, Long paymentId) {
        OrderEntity order = orderRepository.findById(orderId).orElseThrow();
        order.setFkPayment(paymentId);
        orderRepository.save(order);
    }

}
