package com.ksi.healthcaresystem.notificationservice.service;

import com.ksi.healthcaresystem.notificationservice.dto.EmailDetailsDto;

public interface EmailDetailsService {
    EmailDetailsDto saveEmailDetails(EmailDetailsDto emailDetailsDto);
}
