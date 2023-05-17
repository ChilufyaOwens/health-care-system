package com.ksi.healthcaresystem.registration.controller;

import com.ksi.healthcaresystem.commons.dto.ApiResponse;
import com.ksi.healthcaresystem.commons.utils.ResponseUtils;
import com.ksi.healthcaresystem.registration.dto.PatientDto;
import com.ksi.healthcaresystem.registration.service.PatientRegistrationService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/registrations")
@RequiredArgsConstructor
public class PatientRegistrationServiceController {

  private final PatientRegistrationService patientRegistrationService;

  /**
   * This API registers new patient
   *
   * @param patientDto patient details
   * @return regitered patient
   */
  @PostMapping
  public ResponseEntity<ApiResponse<PatientDto>> registerPatient(@RequestBody @Valid PatientDto patientDto) {
    ApiResponse<PatientDto> registeredPatient = patientRegistrationService.registerPatient(patientDto);
    return new ResponseEntity<>(registeredPatient, HttpStatus.CREATED);
  }

  /**
   * This API gets all registered patients from the application
   *
   * @return list of registered patients
   */
  @GetMapping
  public ResponseEntity<ApiResponse<List<PatientDto>>> getAllRegisteredPatients(
      @RequestParam(value = "page", defaultValue = ResponseUtils.DEFAULT_PAGE_NUMBER) Integer page,
      @RequestParam(value = "size", defaultValue = ResponseUtils.DEFAULT_PAGE_SIZE) Integer size) {
    try {
      ApiResponse<List<PatientDto>> registeredPatients = patientRegistrationService.getAllRegisteredPatients(page, size);
      return ResponseEntity.ok(registeredPatients);
    } catch (Exception e) {
      return buildErrorResponse();
    }
  }

  private ResponseEntity<ApiResponse<List<PatientDto>>> buildErrorResponse() {
    ApiResponse<List<PatientDto>> errorResponse = ApiResponse.<List<PatientDto>>builder()
            .success(false)
            .message("An error occurred while fetching all registered patients")
            .build();
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

  }

  /**
   * This API gets a registered patient by ID
   *
   * @param patientId ID of the registered company
   * @return patient details
   */
  @GetMapping(value = "{id}")
  public ResponseEntity<ApiResponse<PatientDto>> getRegisteredPatientById(@PathVariable(name = "id") Long patientId) {
    try {
      return ResponseEntity.ok(patientRegistrationService.getRegisteredPatientById(patientId));
    } catch (Exception e) {
      return buildGetPatientByIdErrorResponse();
    }
  }

  private ResponseEntity<ApiResponse<PatientDto>> buildGetPatientByIdErrorResponse() {
    ApiResponse<PatientDto> response = ApiResponse.<PatientDto>builder()
            .success(false)
            .message("An error occurred while fetching patient details")
            .build();
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * This API deletes a registered patient
   *
   * @param patientId ID of a patient to be deleted
   * @return success message
   */
  @DeleteMapping(value = "{id}")
  public ResponseEntity<String> deletePatient(@PathVariable(name = "id") Long patientId) {
    //Delete patient with given id
    patientRegistrationService.deleteRegisteredPatient(patientId);
    return new ResponseEntity<>("Patient successfully deleted", HttpStatus.OK);
  }

}
