package com.ksi.healthcaresystem.registration.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patient_emergency_contact")
public class EmergencyContact extends Auditable<Long>{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @Cascade(CascadeType.SAVE_UPDATE)
  @MapsId
  @JoinColumn(name = "patient_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
  @Exclude
  private Patient patient;

  @Column(name = "first_name", nullable = false)
  private String firstName;
  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "other_name")
  private String otherName;

  @Column(name = "relationship", nullable = false)
  private String relationship;

  @Column(name = "contact_number", nullable = false)
  private String contactNumber;

  @Column(name = "family_doctor_first_name")
  private String familyDocFirstName;

  @Column(name = "family_doctor_last_name")
  private String familyDocLastName;

  @Column(name = "family_doctor_contact_number")
  private String familyDocContactNumber;

  @Column(name = "pharmacy_name")
  private String preferredPharmacy;

  @Column(name = "pharmacy_contact_number")
  private String pharmacyContactNumber;
}
