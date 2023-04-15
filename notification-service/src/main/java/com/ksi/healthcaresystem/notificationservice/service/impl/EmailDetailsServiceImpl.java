package com.ksi.healthcaresystem.notificationservice.service.impl;

import com.ksi.healthcaresystem.notificationservice.dto.EmailDetailsDto;
import com.ksi.healthcaresystem.notificationservice.service.EmailDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2(topic = "EMAIL_DETAILS_SERVICE")
public class EmailDetailsServiceImpl implements EmailDetailsService {
    @Override
    public String saveEmailDetails(EmailDetailsDto emailDetailsDto) {
        return null;
    }
}
