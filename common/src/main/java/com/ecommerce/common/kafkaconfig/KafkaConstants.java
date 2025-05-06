package com.ecommerce.common.kafkaconfig;

public final class KafkaConstants {
    private KafkaConstants() {}

    public static final String KAFKA_CONNECTION_STRING = System.getenv("KAFKA_URL");
}
