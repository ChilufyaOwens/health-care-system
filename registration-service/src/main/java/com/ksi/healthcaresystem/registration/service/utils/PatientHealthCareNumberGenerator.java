package com.ksi.healthcaresystem.registration.service.utils;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Random;

public class PatientHealthCareNumberGenerator {
  private static final String REFERENCE_NUMBER_PREFIX = "11216";
  private static final int REFERENCE_NUMBER_LENGTH = 13;
  private static final String NUMERIC_STRING = "0123456789";
  private static final Random RANDOM = new SecureRandom();

  private PatientHealthCareNumberGenerator(){}

  /**
   * This method that generates a unique reference number containing only numbers with
   * the code 11216 and current year contained. We add the prefix "11216"
   * to the reference number, followed by the current year obtained using
   * the LocalDate.now().getYear() method. We then generate the remaining digits
   * randomly using the NUMERIC_STRING constant.
   * @return generated health care reference number
   */
  public static String generatePatientHealthCareNumber(){
    StringBuilder builder = new StringBuilder();

    // Add prefix
    builder.append(REFERENCE_NUMBER_PREFIX);
    // Add current year
    int year = LocalDate.now().getYear();
    builder.append(year);

    // Add random digits
    int remainingDigits = REFERENCE_NUMBER_LENGTH - REFERENCE_NUMBER_PREFIX.length() - String.valueOf(year).length();
    for (int i = 0; i < remainingDigits; i++) {
      int index = RANDOM.nextInt(NUMERIC_STRING.length());
      builder.append(NUMERIC_STRING.charAt(index));
    }

    return builder.toString();
  }

}
