package com.ecommerce.common.kafkaconfig;


import com.ecommerce.common.events.*;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class CreateTopics {

    @Bean
    public NewTopic topic_RealiseOrderEvent() {
        return TopicBuilder.name(new RealiseOrderEvent().getTopic())
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topic_AddBasketDataToOrderEvent() {
        return TopicBuilder.name(new AddBasketDataToOrderEvent().getTopic())
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topic_CreateShipmentEvent() {
        return TopicBuilder.name(new CreateShipmentEvent().getTopic())
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topic_RealisePaymentEvent() {
        return TopicBuilder.name(new RealisePaymentEvent().getTopic())
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topic_UpdateInventoryEvent() {
        return TopicBuilder.name(new UpdateInventoryEvent().getTopic())
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topic_AddPaymentDataToOrderEvent() {
        return TopicBuilder.name(new AddPaymentDataToOrderEvent().getTopic())
                .partitions(1)
                .replicas(1)
                .build();
    }

}
