package com.ksi.healthcaresystem.notificationservice.service.kafka;

import com.ksi.healthcaresystem.commons.dto.MessageDto;
import com.ksi.healthcaresystem.notificationservice.dto.EmailDetailsDto;
import com.ksi.healthcaresystem.notificationservice.service.EmailDetailsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Log4j2(topic = "NOTIFICATION_CONSUMER")
@KafkaListener(id = "notification",
        topics = "healthcare-notification-topic",
        groupId = "healthcare-notification")
public class NotificationServiceConsumer {
    private final EmailDetailsService emailDetailsService;
    String topicName = "healthcare-notification-topic";

    @KafkaHandler
    public void handlePatientRegistrationMessage(@NonNull MessageDto messageDto) {
        log.info("Consuming message from -> {}, for patient with healthCareNumber -> {}",
                topicName, messageDto.getHealthCareNumber());
        //Save to the database
        EmailDetailsDto emailDetailsDto = EmailDetailsDto.builder()
                .recipientEmail(messageDto.getEmail())
                .subject("Patient Registration Confirmation")
                .message(buildEmailMessageTemplate(messageDto))
                .attachment(new ArrayList<>())
                .build();

        emailDetailsService.saveEmailDetails(emailDetailsDto);
    }

    /**
     * This message builds the message template that will be sent to the patients email after a successful registration
     * @param messageDto dto with patients details {@link MessageDto}
     * @return message template using stringBuilder
     */
    @NotNull
    private String buildEmailMessageTemplate(MessageDto messageDto) {
        return "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "<title>Patient Registration Confirmation</title>" +
                "<style>" +
                "body {\n" +
                "\t\t\tfont-family: Arial, sans-serif;\n" +
                "\t\t\tbackground-color: #f2f2f2;\n" +
                "\t\t}" +
                ".container {\n" +
                "\t\t\tpadding: 20px;\n" +
                "\t\t\tbackground-color: #ffffff;\n" +
                "\t\t\tborder-radius: 5px;\n" +
                "\t\t\tbox-shadow: 0 0 10px rgba(0, 0, 0, 0.3);\n" +
                "\t\t\tmax-width: 600px;\n" +
                "\t\t\tmargin: auto;\n" +
                "\t\t}" +
                "h1 {\n" +
                "\t\t\tfont-size: 24px;\n" +
                "\t\t\tmargin-top: 0;\n" +
                "\t\t}" +
                "p {\n" +
                "\t\t\tfont-size: 16px;\n" +
                "\t\t\tmargin-bottom: 10px;\n" +
                "\t\t}" +
                ".button {\n" +
                "\t\t\tbackground-color: #4CAF50;\n" +
                "\t\t\tborder: none;\n" +
                "\t\t\tcolor: white;\n" +
                "\t\t\tpadding: 10px 20px;\n" +
                "\t\t\ttext-align: center;\n" +
                "\t\t\ttext-decoration: none;\n" +
                "\t\t\tdisplay: inline-block;\n" +
                "\t\t\tfont-size: 16px;\n" +
                "\t\t\tborder-radius: 5px;\n" +
                "\t\t\tmargin-top: 10px;\n" +
                "\t\t}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class=\"container\">" +
                "<h1>Patient Registration Confirmation</h1>" +
                "<p>Dear " + messageDto.getFirstName() + ",</p>" +
                "<p>We are pleased to inform you that you have been successfully registered in our healthcare system with healthcare number <strong>" + messageDto.getHealthCareNumber() + "</strong>. We want to extend a warm welcome to our medical community and we are thrilled to have you as a part of it.</p>" +
                "<p>As a registered patient, you will have access to our world-class medical facilities and services. We pride ourselves in providing the best possible care to our patients and ensuring their well-being.</p>" +
                "<p>Please keep this email as a record of your registration. In case you have any questions or concerns, please do not hesitate to reach out to us at healthcare@ksi.com.</p>" +
                "<a href=\"#\" class=\"button\">Visit Our Website</a>" +
                "<p>Thank you for choosing KSI healthcare for your healthcare needs. We look forward to serving you.</p>" +
                "<p>Sincerely,</p>" +
                "<p>KSI Healthcare</p>" +
                "</div>" +
                "</body>" +
                "</html>";
    }
}
