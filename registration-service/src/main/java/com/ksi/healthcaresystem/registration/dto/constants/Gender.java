package com.ksi.healthcaresystem.registration.dto.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
  MALE("Male"),
  FEMALE("Female"),
  NOT_APPLICABLE("Prefer not to say");
  private String gender;
}
