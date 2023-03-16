package com.ksi.healthcaresystem.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PatientRegistrationService {

  public static void main(String[] args) {
    SpringApplication.run(PatientRegistrationService.class, args);
  }
}
