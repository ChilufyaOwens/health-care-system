package com.ksi.healthcaresystem.bedservice.entity;

import com.ksi.healthcaresystem.bedservice.dto.constants.BedType;
import com.ksi.healthcaresystem.commons.constants.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Component
@Table(name = "bed")
public class Bed extends Auditable<Long>{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  @Column(name = "code", nullable = false)
  private String bedCode;
  @Column(name = "bed_type", nullable = false)
  @Enumerated(EnumType.STRING)
  private BedType bedType;
  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private Status status;
}
