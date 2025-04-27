package com.ecommerce.paymentservice.infrastructure.eventhandler;

import com.ecommerce.common.events.AddPaymentDataToOrderEvent;
import com.ecommerce.common.events.RealisePaymentEvent;
import com.ecommerce.paymentservice.domain.DTO.PaymentDTO;
import com.ecommerce.paymentservice.domain.entity.PaymentEntity;
import com.ecommerce.paymentservice.infrastructure.service.PaymentCommandService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Component
public class EventHandler {

    private final PaymentCommandService paymentCommandService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final Logger logger = LogManager.getLogger(EventHandler.class);

    public EventHandler(PaymentCommandService paymentCommandService, KafkaTemplate<String, Object> kafkaTemplate) {
        this.paymentCommandService = paymentCommandService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "RealisePaymentEvent", groupId = "payment-group")
    @Transactional
    public void consume(RealisePaymentEvent realisePaymentEvent) {
        logger.info("{} consumer received new message", realisePaymentEvent.getTopic());

        // save payment to database
        PaymentEntity payment = paymentCommandService.createPayment(new PaymentDTO(realisePaymentEvent.getOrderId(), realisePaymentEvent.getClientId(), realisePaymentEvent.getAmount()));
        logger.info("Payment for order: {} created with amount: {}", realisePaymentEvent.getOrderId(), realisePaymentEvent.getAmount());

        // send event to order service to add payment id to order record
        AddPaymentDataToOrderEvent addPaymentEvent = new AddPaymentDataToOrderEvent(realisePaymentEvent.getOrderId(), payment.getId());
        kafkaTemplate.send(addPaymentEvent.getTopic(), addPaymentEvent);
        logger.info("{} sent to other microservices", addPaymentEvent.getTopic());
    }

}
