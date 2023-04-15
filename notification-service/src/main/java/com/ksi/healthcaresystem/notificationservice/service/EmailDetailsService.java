package com.ksi.healthcaresystem.notificationservice.service;

import com.ksi.healthcaresystem.notificationservice.dto.EmailDetailsDto;

public interface EmailDetailsService {
    String saveEmailDetails(EmailDetailsDto emailDetailsDto);
}
