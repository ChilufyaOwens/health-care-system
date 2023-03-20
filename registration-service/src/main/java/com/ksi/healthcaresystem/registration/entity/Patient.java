package com.ksi.healthcaresystem.registration.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ksi.healthcaresystem.registration.dto.constants.Gender;
import com.ksi.healthcaresystem.registration.dto.constants.MaritalStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties("hibernateLazyInitializer")
@Entity
@Table(name = "patient")
public class Patient extends Auditable<Long>{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "health_care_number", nullable = false)
  private String healthCareNumber;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "other_name")
  private String otherName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "dob")
  private LocalDate dateOfBirth;

  @Column(name = "identification_number")
  private String identificationNumber;

  @Enumerated(EnumType.STRING)
  @Column(name = "gender", nullable = false)
  private Gender gender;

  @Column(name = "contact_number")
  private String contactNumber;

  @Column(name = "email", unique = true)
  private String email;

  @Enumerated(EnumType.STRING)
  @Column(name = "marital_status")
  private MaritalStatus maritalStatus;

}
