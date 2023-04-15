package com.ksi.healthcaresystem.notificationservice.service.impl;

import com.ksi.healthcaresystem.notificationservice.service.EmailSenderService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2(topic = "EMAIL_SENDER_SERVICE")
public class EmailSenderServiceImpl implements EmailSenderService {
    private final JavaMailSender javaMailSender;

    /**
     * This method sends email to a patient after a success registration
     * @param recipientEmail patient email address
     * @param subject email subject
     * @param message message to send
     * @throws MessagingException exception
     */
    @Override
    public void sendEmail(String recipientEmail, String subject, String message) throws MessagingException {
        log.info("Sending mail to {} and subject {}", recipientEmail, subject);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(recipientEmail);
        helper.setSubject(subject);
        helper.setText(message, true);

        javaMailSender.send(mimeMessage);
    }

    /**
     * This method sends email with attachments after a successful registration of a patient
     * @param recipientEmail patient email
     * @param subject email subject
     * @param message email message
     * @param attachments attached documents
     * @throws MessagingException messaging exception
     */
    @Override
    public void sendEmail(String recipientEmail, String subject, String message, List<File> attachments) throws MessagingException {
        log.info("Sending mail to {} and subject {}", recipientEmail, subject);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(recipientEmail);
        helper.setSubject(subject);
        helper.setText(message, true);

        //Loop through a list of attachments
        attachments.forEach(file -> {
            try {
                helper.addAttachment(file.getName(), file);
            } catch (MessagingException e) {
                log.error("Failed to attach file to the email");
            }
        });
        javaMailSender.send(mimeMessage);
    }
}
