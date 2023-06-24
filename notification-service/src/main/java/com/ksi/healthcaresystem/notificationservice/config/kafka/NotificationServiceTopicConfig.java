package com.ksi.healthcaresystem.notificationservice.config.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class NotificationServiceTopicConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String healthcareKafkaBootstrapServers;

    @Bean
    public KafkaAdmin kafkaAdmin(){
        Map<String, Object> props = new HashMap<>();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, healthcareKafkaBootstrapServers);
        return new KafkaAdmin(props);
    }

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name("healthcare-notification-topic")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
