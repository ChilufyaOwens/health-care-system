package com.ksi.healthcaresystem.ward.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.ToString;
import lombok.ToString.Exclude;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "room")
@Entity
@ToString
public class Room extends Auditable<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    @Size(max = 50)
    private String name;

    @Column(name = "room_number", nullable = false)
    @Size(max = 20)
    private String roomNumber;

    @Column(name = "code")
    @Size(max = 50)
    private String code;

    @Column(name = "capacity")
    private Integer capacity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ward_id", referencedColumnName = "id", nullable = false)
    @Exclude
    private Ward ward;

}
