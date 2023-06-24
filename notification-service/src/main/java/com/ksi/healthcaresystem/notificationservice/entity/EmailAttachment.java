package com.ksi.healthcaresystem.notificationservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.ToString.Exclude;
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "email_attachments")
public class EmailAttachment extends Auditable<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,
            optional = false,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "email_details_id", referencedColumnName = "id", nullable = false)
    @Exclude
    private EmailDetails emailDetails;
}
