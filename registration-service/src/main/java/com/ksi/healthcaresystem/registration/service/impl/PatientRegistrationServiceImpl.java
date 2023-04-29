package com.ksi.healthcaresystem.registration.service.impl;

import com.ksi.healthcaresystem.commons.events.HealthCareSystemEvent;
import com.ksi.healthcaresystem.commons.exceptions.ResourceNotFoundException;
import com.ksi.healthcaresystem.registration.dto.AddressDto;
import com.ksi.healthcaresystem.registration.dto.EmergencyContactDto;
import com.ksi.healthcaresystem.registration.dto.InsuranceDto;
import com.ksi.healthcaresystem.registration.dto.PatientDto;
import com.ksi.healthcaresystem.registration.entity.Patient;
import com.ksi.healthcaresystem.registration.event.PatientRegistrationEvent;
import com.ksi.healthcaresystem.registration.mapper.PatientMapper;
import com.ksi.healthcaresystem.registration.repository.PatientRepository;
import com.ksi.healthcaresystem.registration.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2(topic = "PATIENT_REGISTRATION_SERVICE")
public class PatientRegistrationServiceImpl implements PatientRegistrationService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final PatientAddressService patientAddressService;
    private final EmergencyContactService emergencyContactService;
    private final PatientInsuranceService patientInsuranceService;
    private final HealCareNumberGeneratorService healCareNumberGeneratorService;
    private final ApplicationEventPublisher eventPublisher;


    /**
     * This method registers new patient
     *
     * @param patientDto patient registration details
     * @return registered patient
     */
    @Override
    public PatientDto registerPatient(PatientDto patientDto) {
        log.info("Registering new patient with details: {}", patientDto);
        Patient mappedPatient = patientMapper.toEntity(patientDto);
        //Create a patient object
        Patient patient = getPatient(mappedPatient);
        patient.setHealthCareNumber(healCareNumberGeneratorService.generatePatientHealthCareNumber());

        Patient registeredPatient = patientRepository.save(patient);
        PatientDto savedPatient = patientMapper.toDto(registeredPatient);

        //Save patient address
        AddressDto savedAddress = patientAddressService.savePatientAddress(patientDto.getAddress(), registeredPatient);
        savedPatient.setAddress(savedAddress);

        //Save patient emergency contacts
        List<EmergencyContactDto> emergencyContacts = emergencyContactService.saveEmergencyContacts(
                patientDto.getEmergencyContacts(), registeredPatient);
        savedPatient.setEmergencyContacts(new HashSet<>(emergencyContacts));

        //Save only if patient has insurance
        if (!patientDto.getInsurances().isEmpty()) {
            List<InsuranceDto> insurances = patientInsuranceService.savePatientInsurances(
                    patientDto.getInsurances().stream().toList(), registeredPatient);
            savedPatient.setInsurances(new HashSet<>(insurances));
        }

        //Publish event
        PatientRegistrationEvent patientRegistrationEvent = new PatientRegistrationEvent(this, savedPatient);
        HealthCareSystemEvent<PatientRegistrationEvent> healthCareSystemEvent = new HealthCareSystemEvent<>(patientRegistrationEvent);
        eventPublisher.publishEvent(healthCareSystemEvent);

        return savedPatient;
    }

    /**
     * This method builds the patient object to be saved
     *
     * @param mappedPatient mapped patient details
     * @return new patient
     */
    private static Patient getPatient(Patient mappedPatient) {
        //Get health care number
        return new Patient(mappedPatient.getId(), mappedPatient.getHealthCareNumber(), mappedPatient.getFirstName(),
                mappedPatient.getOtherName(), mappedPatient.getLastName(), mappedPatient.getDateOfBirth(),
                mappedPatient.getIdentificationNumber(), mappedPatient.getGender(), mappedPatient.getContactNumber(),
                mappedPatient.getEmail(), mappedPatient.getMaritalStatus());
    }

    /**
     * This method gets all registered patients
     *
     * @return list of all registered patients
     */
    @Cacheable(value = "patientCache")
    @Override
    public List<PatientDto> getAllRegisteredPatients(Integer page, Integer size) {
        log.info("Fetching all registered patients");
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("id")));
        Page<Patient> patients = patientRepository.findAll(pageable);
        List<PatientDto> patientList = new ArrayList<>();
        patients.forEach(patient -> {
            PatientDto patientDto = patientMapper.toDto(patient);
            patientList.add(patientDto);
        });

        return patientList;
    }

    /**
     * This method checks if registered patient record with given id is present in the redis cache, if patient record is
     * present in the cache, return that record, if not present, get the record from the database, insert the new record
     * in the redis cache and return patient record to the user.
     *
     * @param patientId ID of the patient
     * @return patient record or throw a resource not found exception
     */
    @Cacheable(value = "patientCache", key = "#patientId")
    @Override
    public PatientDto getRegisteredPatientById(Long patientId) {
        log.info("Fetching patient with  ID: {}", patientId);


        final Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (optionalPatient.isEmpty()) {
            throw new ResourceNotFoundException("Patient", "id", String.valueOf(patientId));
        }
        //Save patient record in a redis cache
        log.info("Patient registration service findById() : cache insert -> {}",
                patientMapper.toDto(optionalPatient.get()));
        return patientMapper.toDto(optionalPatient.get());
    }

    /**
     * This method deletes registered patient with the supplied patient Id
     *
     * @param patientId ID of the patient to be deleted
     */

    @CacheEvict(value = "patientCache", key = "patientId")
    @Override
    public void deleteRegisteredPatient(Long patientId) {
        log.info("Deleting patient with ID: {}", patientId);
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        optionalPatient.ifPresentOrElse(patientRepository::delete, () -> {
            throw new ResourceNotFoundException("Patient", "id", String.valueOf(patientId));
        });
    }
}
