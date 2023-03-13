package com.ksi.healthcaresystem.bedservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BedServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(BedServiceApplication.class, args);
  }
}
