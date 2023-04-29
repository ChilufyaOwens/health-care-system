package com.ksi.healthcaresystem.commons.dto;

import lombok.*;

import java.io.File;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String otherName;
    private String healthCareNumber;
    private String contactNumber;
}
