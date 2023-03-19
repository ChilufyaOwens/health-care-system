package com.ksi.healthcaresystem.registration.exception;

import com.ksi.healthcaresystem.commons.constants.ExceptionCode;
import com.ksi.healthcaresystem.commons.dto.ErrorDetails;
import com.ksi.healthcaresystem.commons.exception.ResourceNotFoundException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * This method handles resource not found exception
   * @param exception not found exception
   * @param webRequest web request
   * @return exception not found response entity
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
    ErrorDetails errorDetails = ErrorDetails.builder()
        .timestamp(LocalDateTime.now())
        .message(exception.getMessage())
        .path(webRequest.getDescription(false))
        .errorCode(ExceptionCode.PATIENT_NOT_FOUND.toString())
        .build();

    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  /**
   * This method hanles email already exists exception
   * @param exception email already exists exception
   * @param webRequest web request
   * @return email already exists exception
   */
  @ExceptionHandler(EmailAlreadyExistsException.class)
  public ResponseEntity<ErrorDetails> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception, WebRequest webRequest){
    ErrorDetails errorDetails = ErrorDetails.builder()
        .timestamp(LocalDateTime.now())
        .message(exception.getMessage())
        .path(webRequest.getDescription(false))
        .errorCode(ExceptionCode.EMAIL_ALREADY_EXISTS.toString())
        .build();

    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  /**
   * This method handles patient identification already exists exception
   * @param exception patient id already exists exception
   * @param webRequest request
   * @return patient identification already exists exception
   */
  @ExceptionHandler(PatientIdentificationNumberAlreadyExistsException.class)
  public ResponseEntity<ErrorDetails> handPatientIdAlreadyExistsException(PatientIdentificationNumberAlreadyExistsException exception,
      WebRequest webRequest){
    ErrorDetails errorDetails = ErrorDetails.builder()
        .timestamp(LocalDateTime.now())
        .message(exception.getMessage())
        .path(webRequest.getDescription(false))
        .errorCode(ExceptionCode.PATIENT_ID_ALREADY_EXISTS.toString())
        .build();

    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  /**
   * This method handle global or generic exceptions
   * @param exception generic exception
   * @param webRequest web request
   * @return generic exception
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetails>handleGlobalException(Exception exception, WebRequest webRequest){
    ErrorDetails errorDetails = ErrorDetails.builder()
        .timestamp(LocalDateTime.now())
        .message(exception.getMessage())
        .path(webRequest.getDescription(false))
        .errorCode(ExceptionCode.INTERNAL_SERVER_ERROR.toString())
        .build();

    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * This method handles method args validation exceptions
   * @param ex exception
   * @param headers header
   * @param status status
   * @param request request
   * @return validation exception
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    Map<String,String> errorMap = new HashMap<>();
    List<ObjectError> errorList = ex.getBindingResult().getAllErrors();

    errorList.forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String message = error.getDefaultMessage();

      errorMap.put(fieldName, message);
    });

    return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
  }
}
