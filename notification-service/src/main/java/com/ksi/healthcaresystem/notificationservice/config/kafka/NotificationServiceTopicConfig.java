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
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, healthcareKafkaBootstrapServers);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name("notification")
                .partitions(10)
                .replicas(1)
                .build();
    }
}