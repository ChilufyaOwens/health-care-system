package com.ksi.healthcaresystem.notificationservice.event.listener;

import com.ksi.healthcaresystem.commons.constants.Status;
import com.ksi.healthcaresystem.commons.events.HealthCareSystemEvent;
import com.ksi.healthcaresystem.notificationservice.dto.EmailDetailsDto;
import com.ksi.healthcaresystem.notificationservice.entity.EmailDetails;
import com.ksi.healthcaresystem.notificationservice.event.EmailSenderEvent;
import com.ksi.healthcaresystem.notificationservice.repository.EmailDetailsRepository;
import com.ksi.healthcaresystem.notificationservice.service.EmailSenderService;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2(topic = "EMAIL_SENDER_EVENT_LISTENER")
public class EmailSenderEventListener {
    private final EmailSenderService emailSenderService;
    private final EmailDetailsRepository emailDetailsRepository;

    /**
     * This event listener sends email to a patient after a successful registration
     *
     * @param event email sender event
     */
    @EventListener
    public void sendEmailNotification(@NotNull HealthCareSystemEvent<EmailSenderEvent> event) {
        EmailSenderEvent emailSenderEvent = event.getSource();
        EmailDetailsDto emailDetailsDto = emailSenderEvent.getMessage();
        if (emailDetailsDto == null) {
            return;
        }
        EmailDetails emailDetails = getEmailDetailsById(emailDetailsDto.getId());
        if (emailDetails == null) {
            return;
        }
        log.info("Sending email to registered patient with email address -> {}", emailDetails.getRecipientEmail());
        if (emailDetailsDto.getAttachment() == null) {
            //Send email to registered patient
            try {
                emailSenderService.sendRegistrationConfirmationEmail(
                        emailDetailsDto.getRecipientEmail(),
                        emailDetailsDto.getSubject(),
                        emailDetailsDto.getMessage()
                );
            } catch (MessagingException e) {
                log.error(e.getMessage(), e.getCause());
                emailDetails.setStatus(Status.FAILED);
                emailDetailsRepository.save(emailDetails);
            }
        } else {
            //Send email to registered patient with attachments
            try {
                emailSenderService.sendRegistrationConfirmationEmail(
                        emailDetailsDto.getRecipientEmail(),
                        emailDetailsDto.getSubject(),
                        emailDetailsDto.getMessage(),
                        emailDetailsDto.getAttachment()
                );
            } catch (MessagingException e) {
                log.error(e.getMessage(), e.getCause());
                emailDetails.setStatus(Status.FAILED);
                emailDetailsRepository.save(emailDetails);
            }
        }
        emailDetails.setStatus(Status.SENT);
        emailDetailsRepository.save(emailDetails);
    }

    /**
     * This method get email details by Id
     *
     * @param emailDetailsId email details id
     * @return emailDetails
     */
    private EmailDetails getEmailDetailsById(Long emailDetailsId) {
        return emailDetailsRepository.findById(emailDetailsId).orElse(null);
    }
}
