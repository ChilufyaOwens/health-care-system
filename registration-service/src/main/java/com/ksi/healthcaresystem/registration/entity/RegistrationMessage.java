package com.ksi.healthcaresystem.registration.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "registration_message")
@Entity
public class RegistrationMessage extends Auditable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "status")
    @Size(max = 10)
    private String status;

    @Size(max = 13)
    @Column(name = "health_care_number", nullable = false, unique = true)
    private String healthCareNumber;

    @Email
    @Column(name = "email")
    private String email;
}
