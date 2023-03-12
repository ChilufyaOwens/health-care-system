package com.ksi.healthcaresystem.bedservice.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BedDto {
  private Long id;
  @NotEmpty(message = "Bed code should not be empty or null")
  private String bedCode;
  @NotEmpty(message = "Bed type should not be empty or null")
  private String bedType;
  @NotEmpty(message = "Bed status should not be empty or null")
  private String status;
}
