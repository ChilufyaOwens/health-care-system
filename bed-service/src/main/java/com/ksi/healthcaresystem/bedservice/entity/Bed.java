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
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "bed")
public class Bed {

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

  @Column(name = "created_at", nullable = false)
  @CreatedDate
  private LocalDateTime createdAt;

  @Column(name = "created_by")
  private Long createdBy;

  @Column(name = "modified_at")
  @LastModifiedDate
  private LocalDateTime modifiedAt;

  @Column(name = "modified_by")
  private Long modifiedBy;
}
