package com.ksi.healthcaresystem.registration.dto;

import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A DTO for the {@link com.ksi.healthcaresystem.registration.entity.Address} entity
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressDto implements Serializable {
  private Long id;
  @NotEmpty(message = "Address line one should not be null")
  private String streetAddressLineOne;
  private String streetAddressLineTwo;
  @NotEmpty(message = "City should not be empty or null")
  private String city;
  private String stateProvince;
  private String zipCode;
  private String country;
}
