package com.ksi.healthcaresystem.notificationservice.entity;

import com.ksi.healthcaresystem.commons.constants.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;
import lombok.ToString.Exclude;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "email_details")
public class EmailDetails extends Auditable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Email
    @NotEmpty
    @Column(name = "recipient_email", nullable = false)
    private String recipientEmail;

    @Column(name = "subject", nullable = false)
    private String subject;
    @Size(max = 4000, message = "message should not be more than 4000 characters")
    @Column(name = "message")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToMany(
            mappedBy = "emailDetails",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @Exclude
    private Set<EmailAttachment> emailAttachments;
}
