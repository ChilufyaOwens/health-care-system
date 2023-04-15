package com.ksi.healthcaresystem.notificationservice.config.kafka;

import com.ksi.healthcaresystem.commons.dto.MessageDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class NotificationServiceProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String healthcareKafkaBootstrapServers;

    @Bean
    public ProducerFactory<String, MessageDto> messageProducerFactory(){
        return new DefaultKafkaProducerFactory<>(producerProps());
    }

    private Map<String, Object> producerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, healthcareKafkaBootstrapServers);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 10);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public KafkaTemplate<String, MessageDto> kafkaTemplate(ProducerFactory<String, MessageDto> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }
}
