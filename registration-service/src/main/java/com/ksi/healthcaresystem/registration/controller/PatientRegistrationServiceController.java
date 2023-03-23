package com.ksi.healthcaresystem.registration.controller;

import com.ksi.healthcaresystem.registration.dto.PatientDto;
import com.ksi.healthcaresystem.registration.service.PatientRegistrationService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
   * @param patientDto
   * @return
   */
  @PostMapping
  public ResponseEntity<PatientDto> registerPatient(@RequestBody @Valid PatientDto patientDto){
    PatientDto registeredPatient = patientRegistrationService.registerPatient(patientDto);
    return new ResponseEntity<>(registeredPatient, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<PatientDto>> getAllRegisteredPatients(){
    List<PatientDto> registeredPatients = patientRegistrationService.getAllRegisteredPatients();
    return new ResponseEntity<>(registeredPatients, HttpStatus.OK);
  }

}
