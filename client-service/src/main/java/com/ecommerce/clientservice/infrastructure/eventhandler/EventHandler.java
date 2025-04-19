package com.ecommerce.clientservice.infrastructure.eventhandler;

import com.ecommerce.clientservice.domain.DTO.BasketDTO;
import com.ecommerce.clientservice.domain.DTO.CreditCardDTO;
import com.ecommerce.clientservice.domain.entity.ClientEntity;
import com.ecommerce.clientservice.domain.entity.CreditCardEntity;
import com.ecommerce.clientservice.domain.event.*;
import com.ecommerce.clientservice.infrastructure.service.ClientCommandService;
import com.ecommerce.clientservice.infrastructure.service.ClientQueryService;
import com.ecommerce.clientservice.infrastructure.service.ReservationQueryService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class EventHandler {

    private final ReservationQueryService reservationQueryService;
    private final ClientQueryService clientQueryService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public EventHandler(ReservationQueryService reservationQueryService, ClientQueryService clientQueryService, KafkaTemplate<String, Object> kafkaTemplate) {
        this.reservationQueryService = reservationQueryService;
        this.clientQueryService = clientQueryService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "RealiseOrderEvent", groupId = "client-group")
    @Transactional
    public void
    consume(RealiseOrderEvent realiseOrderEvent)
    {
        BasketDTO basket = reservationQueryService.getClientBasket(realiseOrderEvent.getClientId());
        ClientEntity client = clientQueryService.getClient(realiseOrderEvent.getClientId());

        // sending event with basket data to order microservice
        AddBasketDataToOrderEvent updateOrderEvent = new AddBasketDataToOrderEvent(basket);
        kafkaTemplate.send(updateOrderEvent.getTopic(), updateOrderEvent);

        // sending event asking for inventory update
        UpdateInventoryEvent updateInventoryEvent = new UpdateInventoryEvent(basket);
        kafkaTemplate.send(updateInventoryEvent.getTopic(), updateInventoryEvent);

        // sending event asking for payment processing
        CreditCardEntity creditCard = client.getCreditCard();
        CreditCardDTO creditCardDTO = new CreditCardDTO(creditCard.getNumber(), creditCard.getCVV(), creditCard.getExpirationDate());
        RealisePaymentEvent realisePaymentEvent = new RealisePaymentEvent(realiseOrderEvent.getOrderId(), client.getId(), creditCardDTO);
        kafkaTemplate.send(realisePaymentEvent.getTopic(), realisePaymentEvent);

        // sending event asking for shipment creation
        CreateShipmentEvent createShipmentEvent = new CreateShipmentEvent(realisePaymentEvent.getOrderId(), client.getAddress());
        kafkaTemplate.send(createShipmentEvent.getTopic(), createShipmentEvent);
    }

}
