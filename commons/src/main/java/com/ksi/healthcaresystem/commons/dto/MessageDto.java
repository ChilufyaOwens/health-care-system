package com.ksi.healthcaresystem.commons.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private String email;
    private String firstName;
    private String lastName;
    private String otherName;
    private String healthCareNumber;
    private String contactNumber;
}
