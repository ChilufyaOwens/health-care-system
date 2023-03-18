package com.ksi.healthcaresystem.registration.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;

/**
 * A DTO for the {@link com.ksi.healthcaresystem.registration.entity.Address} entity
 */
@Data
public class AddressDto implements Serializable {

  private final Long id;
  @NotNull(message = "Patient details should not be null")
  private final PatientDto patient;

  @NotEmpty(message = "Address line one should not be null")
  private final String streetAddressLineOne;
  private final String streetAddressLineTwo;
  @NotEmpty(message = "City should not be empty or null")
  private final String city;
  private final String stateProvince;
  private final String zipCode;
  private final String country;
}