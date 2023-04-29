package com.ksi.healthcaresystem.registration.event.listener;

import com.ksi.healthcaresystem.commons.constants.Status;
import com.ksi.healthcaresystem.commons.dto.MessageDto;
import com.ksi.healthcaresystem.commons.events.HealthCareSystemEvent;
import com.ksi.healthcaresystem.registration.dto.PatientDto;
import com.ksi.healthcaresystem.registration.entity.RegistrationMessage;
import com.ksi.healthcaresystem.registration.event.PatientRegistrationEvent;
import com.ksi.healthcaresystem.registration.repository.RegistrationMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Log4j2(topic = "PATIENT_REGISTRATION_LISTENER")
public class PatientRegistrationListener {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final RegistrationMessageRepository messageRepository;

    @Value("${spring.kafka.template.default-topic}")
    private String topicName;

    @EventListener
    public void sendEmail(HealthCareSystemEvent<PatientRegistrationEvent> event) {
        PatientRegistrationEvent patientRegistrationEvent = event.getSource();
        PatientDto patientDto = patientRegistrationEvent.getMessage();
        if (patientDto == null) {
            return;
        }
        MessageDto messageDto = MessageDto.builder()
                .firstName(patientDto.getFirstName())
                .lastName(patientDto.getLastName())
                .otherName(patientDto.getOtherName())
                .email(patientDto.getEmail())
                .healthCareNumber(patientDto.getHealthCareNumber())
                .contactNumber(patientDto.getContactNumber())
                .build();
        //Save the message before sending to the kafka broker
        RegistrationMessage registrationMessage = savePatientRegistrationSentMessage(messageDto);

        //publish message to the kafka broker
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(
                topicName,
                messageDto
        );
        future.whenComplete((result, exception) -> {
            if (exception == null) {
                log.info("Message sent -> {}, to topic -> {}, with offset -> {}", messageDto, topicName, result.getRecordMetadata().offset());
                //Update message sent status
                registrationMessage.setStatus(Status.PROCESSED.getStatusCode());
            } else {
                log.error("Unable to send message -> {}, due to -> {}", messageDto, exception.getMessage());
                registrationMessage.setStatus(Status.FAILED.getStatusCode());
            }
        });
        messageRepository.save(registrationMessage);
    }


    /**
     * This method saves the message published to the kafka broker
     *
     * @param messageDto message details
     * @return saved message
     */
    private RegistrationMessage savePatientRegistrationSentMessage(MessageDto messageDto) {
        return messageRepository.save(
                RegistrationMessage.builder()
                        .email(messageDto.getEmail())
                        .healthCareNumber(messageDto.getHealthCareNumber())
                        .build()
        );
    }
}
