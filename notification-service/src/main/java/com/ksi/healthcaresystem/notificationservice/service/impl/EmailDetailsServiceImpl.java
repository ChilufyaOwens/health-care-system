package com.ksi.healthcaresystem.notificationservice.service.impl;

import com.ksi.healthcaresystem.commons.events.HealthCareSystemEvent;
import com.ksi.healthcaresystem.notificationservice.dto.EmailDetailsDto;
import com.ksi.healthcaresystem.notificationservice.entity.EmailDetails;
import com.ksi.healthcaresystem.notificationservice.event.EmailSenderEvent;
import com.ksi.healthcaresystem.notificationservice.mapper.EmailDetailsMapper;
import com.ksi.healthcaresystem.notificationservice.repository.EmailDetailsRepository;
import com.ksi.healthcaresystem.notificationservice.service.EmailDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2(topic = "EMAIL_DETAILS_SERVICE")
public class EmailDetailsServiceImpl implements EmailDetailsService {
    private final EmailDetailsRepository emailDetailsRepository;
    private final EmailDetailsMapper emailDetailsMapper;
    private final ApplicationEventPublisher eventPublisher;

    /**
     * This method saves email sent to the patient's email address after a successful registration for reference purpose.
     * @param emailDetailsDto details of email sent to the taxpayer
     * @return email details dto
     */
    @Override
    public EmailDetailsDto saveEmailDetails(@NotNull EmailDetailsDto emailDetailsDto) {
        log.info("Saving patient registration successful email to -> {}", emailDetailsDto.getRecipientEmail());
        EmailDetails emailDetails  =  emailDetailsMapper.toEntity(emailDetailsDto);
        EmailDetails savedEmailMessage = emailDetailsRepository.save(emailDetails);

        EmailDetailsDto savedEmailDetails = emailDetailsMapper.toDto(savedEmailMessage);

        //Publish email details event
        EmailSenderEvent emailSenderEvent = new EmailSenderEvent(this, savedEmailDetails);
        HealthCareSystemEvent<EmailSenderEvent> healthCareSystemEvent = new HealthCareSystemEvent<>(emailSenderEvent);
        eventPublisher.publishEvent(healthCareSystemEvent);

        return savedEmailDetails;
    }
}
