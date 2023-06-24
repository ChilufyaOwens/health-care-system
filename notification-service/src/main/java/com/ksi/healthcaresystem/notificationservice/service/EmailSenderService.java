package com.ksi.healthcaresystem.notificationservice.service;

import jakarta.mail.MessagingException;

import java.io.File;
import java.util.List;

public interface EmailSenderService {
    void sendRegistrationConfirmationEmail(String recipientEmail, String subject, String message) throws MessagingException;
    void sendRegistrationConfirmationEmail(String recipientEmail, String subject, String message, List<File> attachments) throws MessagingException;
}
