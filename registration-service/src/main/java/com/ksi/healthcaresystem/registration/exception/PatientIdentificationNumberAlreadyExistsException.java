package com.ksi.healthcaresystem.registration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PatientIdentificationNumberAlreadyExistsException extends RuntimeException{
  private String message;
  public PatientIdentificationNumberAlreadyExistsException(String message){
    super(message);
    this.message = message;
  }

}
