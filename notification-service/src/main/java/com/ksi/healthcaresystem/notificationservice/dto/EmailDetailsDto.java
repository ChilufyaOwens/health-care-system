package com.ksi.healthcaresystem.notificationservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetailsDto {
    @Email
    @NotEmpty
    private String to;
    @NotEmpty
    private String subject;
    @NotEmpty
    private String message;
}
