package com.ksi.healthcaresystem.registration.service.utils;

import java.time.LocalDate;
import java.util.random.RandomGenerator;

public interface HealthCareNumberGenerator {

  /**
   * This method generate a health care number
   * @return health care reference number
   */
  static String generateHealthCareNumber(){
    RandomGenerator randomGenerator = RandomGenerator.getDefault();
    long numberGenerated = randomGenerator.nextLong(500);
    LocalDate currentDate = LocalDate.now();
    return numberGenerated + "" + currentDate.getYear() + "" + currentDate.getDayOfMonth();
  }
}
