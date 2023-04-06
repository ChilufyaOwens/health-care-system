package com.ksi.healthcaresystem.registration.service.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
  private static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
  private static final Pattern EMAIL_REGEX_PATTERN = Pattern.compile(
      "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

  /**
   * This method takes an email address as input and returns a boolean indicating
   * whether the email address is valid. The email address is validated using a regular
   * expression pattern that matches common email address formats.
   * @param email email address to validate
   * @return true or false
   */
  public static boolean isValidEmailAddress(String email) {
    Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  /**
   * This method takes an email address as input and returns true
   * if the email address is valid according to the regular expression pattern.
   * @param email email address to validate
   * @return (true if valid false if not valid)
   */
  public static boolean isValidEmail(String email){
    return EMAIL_REGEX_PATTERN.matcher(email).matches();
  }
}
