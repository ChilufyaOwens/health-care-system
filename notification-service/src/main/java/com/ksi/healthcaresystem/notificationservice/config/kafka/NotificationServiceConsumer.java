package com.ksi.healthcaresystem.notificationservice.config.kafka;

import com.ksi.healthcaresystem.commons.dto.MessageDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(id = "notification", topics = "notification", groupId = "healthcare-notification")
@Log4j2(topic = "NOTIFICATION_CONSUMER")
public class NotificationServiceConsumer {
    @KafkaHandler
    public void handleMessage(MessageDto messageDto){
        log.info("Consuming message from the kafka topic: {}", messageDto);
        //Save to the database
    }
}
