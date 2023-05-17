package com.ksi.healthcaresystem.ward.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WardDto {
    private Long id;
    private String name;
    private String status;
    private String code;
}
