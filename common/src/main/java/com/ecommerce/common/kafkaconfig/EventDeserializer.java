package com.ecommerce.common.kafkaconfig;

import com.ecommerce.common.events.AddBasketDataToOrderEvent;
import com.ecommerce.common.events.RealiseOrderEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class EventDeserializer implements Deserializer<Object> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Object deserialize(String topic, byte[] data) {
        try {
            switch (topic) {
                case "RealiseOrderEvent":
                    return objectMapper.readValue(data, RealiseOrderEvent.class);
                case "AddBasketDataToOrderEvent":
                    return objectMapper.readValue(data, AddBasketDataToOrderEvent.class);
                default:
                    throw new IllegalArgumentException("Unknown topic: " + topic);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error deserializing message", e);
        }
    }
}
