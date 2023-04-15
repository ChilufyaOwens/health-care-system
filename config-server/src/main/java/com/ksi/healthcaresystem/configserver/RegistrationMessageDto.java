package com.ksi.healthcaresystem.configserver;

import jakarta.validation.constraints.Email;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.ksi.healthcaresystem.registration.entity.RegistrationMessage} entity
 */
@Data
public class RegistrationMessageDto implements Serializable {
    private final String healthCareNumber;
    @Email
    private final String email;
}