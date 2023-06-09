package com.ksi.healthcaresystem.registration.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient_insurance_details")
public class Insurance extends Auditable<Long>{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
  @JoinColumn(name = "patient_id", referencedColumnName = "id", nullable = false)
  @Exclude
  private Patient patient;
  @Column(name = "insurance_company", nullable = false)
  private String insuranceCompany;
  @Column(name = "insurance_number")
  private String insuranceNumber;
  @Column(name = "insurance_holder_first_name", nullable = false)
  private String policyHolderFirstName;
  @Column(name = "insurance_holder_last_name", nullable = false)
  private String policyHolderLastName;
  @Column(name = "dob")
  private LocalDate dateOfBirth;
}
