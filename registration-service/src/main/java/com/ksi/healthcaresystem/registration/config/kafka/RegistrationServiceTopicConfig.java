package com.ksi.healthcaresystem.registration.config.kafka;

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
public class RegistrationServiceTopicConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String healthcareKafkaBootstrapServers;

    @Bean
    KafkaAdmin kafkaAdmin(){
        Map<String, Object> config = new HashMap<>();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, healthcareKafkaBootstrapServers);
        return new KafkaAdmin(config);
    }

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name("")
                .partitions(10)
                .replicas(1)
                .build();
    }

}
