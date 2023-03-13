package com.ksi.healthcaresystem.commons.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
  AVAILABLE ("Available"),
  ASSIGNED("Assigned"),
  NON_FUNCTIONAL("NonFunctional"),
  OCCUPIED("Occupied"),
  TAKEN("Taken");
  private String statusCode;
}
