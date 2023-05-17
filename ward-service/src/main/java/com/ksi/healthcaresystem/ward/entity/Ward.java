package com.ksi.healthcaresystem.ward.entity;

import com.ksi.healthcaresystem.commons.constants.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.ToString;
import lombok.ToString.Exclude;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "ward")
@Entity
public class Ward extends Auditable<Long>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    @Size(max = 100)
    private String name;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Size(max = 20)
    private Status status;

    @Column(name = "code", nullable = false)
    @Size(max = 50)
    private String code;

    @OneToMany(mappedBy = "ward",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @Exclude
    private Set<Room> rooms;

}
