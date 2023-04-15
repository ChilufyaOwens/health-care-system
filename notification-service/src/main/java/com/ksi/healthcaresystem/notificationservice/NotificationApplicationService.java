package com.ksi.healthcaresystem.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NotificationApplicationService {
  public static void main(String[] args) {
    SpringApplication.run(NotificationApplicationService.class, args);
  }
}
