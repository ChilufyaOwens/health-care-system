package com.ksi.healthcaresystem.registration.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "patient_address")
public class Address extends Auditable<Long>{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "patient_id")
  @Exclude
  private Patient patient;
  @Column(name = "street_address_line_one")
  private String streetAddressLineOne;

  @Column(name = "street_address_line_two")
  private String streetAddressLineTwo;
  @Column(name = "city", nullable = false)
  private String city;

  @Column(name = "state_province")
  private String stateProvince;

  @Column(name = "zip_code")
  private String zipCode;

  @Column(name = "country")
  private String country;

}
