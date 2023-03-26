package com.ksi.healthcaresystem.registration.controller;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/registrations")
@RequiredArgsConstructor
public class PatientRegistrationServiceController {
  private final PatientRegistrationService patientRegistrationService;

  /**
   * This API registers new patient
   * @param patientDto patient details
   * @return regitered patient
   */
  @PostMapping
  public ResponseEntity<PatientDto> registerPatient(@RequestBody @Valid PatientDto patientDto){
    PatientDto registeredPatient = patientRegistrationService.registerPatient(patientDto);
    return new ResponseEntity<>(registeredPatient, HttpStatus.CREATED);
  }

  /**
   * This API gets all registered patients from the application
   * @return list of restered patients
   */
  @GetMapping
  public ResponseEntity<List<PatientDto>> getAllRegisteredPatients(){
    List<PatientDto> registeredPatients = patientRegistrationService.getAllRegisteredPatients();
    return new ResponseEntity<>(registeredPatients, HttpStatus.OK);
  }

  /**
   * This API gets a registered patient by ID
   * @param patientId ID of the registered company
   * @return patient details
   */
  @GetMapping(value = "{id}")
  public ResponseEntity<PatientDto> getRegisteredPatientById(@PathVariable(name = "id") Long patientId){
    PatientDto patientDto = patientRegistrationService.getRegisteredPatientById(patientId);
    return new ResponseEntity<>(patientDto, HttpStatus.OK);
  }

  /**
   * This API deletes a registered patient
   * @param patientId ID of a patient to be deleted
   * @return success message
   */
  @DeleteMapping(value = "{id}")
  public ResponseEntity<String> deletePatient(@PathVariable(name = "id") Long patientId){
    //Delete patient with given id
    patientRegistrationService.deleteRegisteredPatient(patientId);
    return new ResponseEntity<>("Patient successfully deleted", HttpStatus.OK);

  }

}
