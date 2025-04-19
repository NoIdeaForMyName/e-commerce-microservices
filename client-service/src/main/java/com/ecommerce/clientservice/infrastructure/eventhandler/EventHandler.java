package com.ecommerce.clientservice.infrastructure.eventhandler;

import com.ecommerce.clientservice.domain.entity.ClientEntity;
import com.ecommerce.clientservice.domain.entity.CreditCardEntity;
import com.ecommerce.clientservice.infrastructure.service.ReservationCommandService;
import com.ecommerce.common.DTO.*;
import com.ecommerce.common.events.*;
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
    private final ReservationCommandService reservationCommandService;

    public EventHandler(ReservationQueryService reservationQueryService, ClientQueryService clientQueryService, KafkaTemplate<String, Object> kafkaTemplate, ReservationCommandService reservationCommandService) {
        this.reservationQueryService = reservationQueryService;
        this.clientQueryService = clientQueryService;
        this.kafkaTemplate = kafkaTemplate;
        this.reservationCommandService = reservationCommandService;
    }

    @KafkaListener(topics = "RealiseOrderEvent", groupId = "client-group")
    @Transactional
    public void
    consume(RealiseOrderEvent realiseOrderEvent)
    {
        BasketDTO basket = reservationQueryService.getClientBasket(realiseOrderEvent.getClientId());
        ClientEntity client = clientQueryService.getClient(realiseOrderEvent.getClientId());

        // sending event with basket data to order microservice
        AddBasketDataToOrderEvent updateOrderEvent = new AddBasketDataToOrderEvent(realiseOrderEvent.getOrderId(), basket);
        kafkaTemplate.send(updateOrderEvent.getTopic(), updateOrderEvent);

        // sending event asking for inventory update
        UpdateInventoryEvent updateInventoryEvent = new UpdateInventoryEvent(realiseOrderEvent.getOrderId(), basket);
        kafkaTemplate.send(updateInventoryEvent.getTopic(), updateInventoryEvent);

        // sending event asking for shipment creation
        CreateShipmentEvent createShipmentEvent = new CreateShipmentEvent(realiseOrderEvent.getOrderId(), client.getAddress());
        kafkaTemplate.send(createShipmentEvent.getTopic(), createShipmentEvent);

        // empty client basket
        reservationCommandService.emptyReservations(client.getId());
    }

}
