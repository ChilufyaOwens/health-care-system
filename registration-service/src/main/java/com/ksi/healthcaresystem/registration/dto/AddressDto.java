package com.ksi.healthcaresystem.registration.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * A DTO for the {@link com.ksi.healthcaresystem.registration.entity.Address} entity
 */
@Data
public class AddressDto implements Serializable {

  private final LocalDateTime createdAt;
  private final Long id;
  private final PatientDto patient;
  private final String streetAddressLineOne;
  private final String streetAddressLineTwo;
  private final String city;
  private final String stateProvince;
  private final String zipCode;
  private final String country;
}