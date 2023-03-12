package com.ksi.healthcaresystem.bedservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BedCodeAlreadyExistsException extends RuntimeException{
  private String message;
  public BedCodeAlreadyExistsException(String message){
    super(message);
    this.message = message;
  }
}
