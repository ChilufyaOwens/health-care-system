package com.ksi.healthcaresystem.notificationservice.dto;

import com.ksi.healthcaresystem.commons.constants.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.File;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailDetailsDto {
    private Long id;
    @Email
    @NotEmpty
    private String recipientEmail;
    @NotEmpty
    private String subject;
    @NotEmpty
    private String message;
    private Status status;
    private List<File> attachment;
}
