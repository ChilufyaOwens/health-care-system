package com.ksi.healthcaresystem.commons.dto;

import java.time.LocalDateTime;
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
public class ErrorDetails {
  private LocalDateTime timestamp;
  private String message;
  private String path;
  private String errorCode;
}
