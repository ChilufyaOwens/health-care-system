package com.ksi.healthcaresystem.bedservice.dto.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BedType {
  GATCH("Gatch Bed"),
  ELECTRIC("Electric Bed"),
  STRETCHER("Stretcher"),
  LOW("Low Bed"),
  LOW_AIR_LOSS("Low Air Loss"),
  CIRCO_ELECTRIC("Circo-electric"),
  CLINITRON("Clinitron");
  private String name;
}
