package com.ksi.healthcaresystem.ward.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U>{
  @CreatedBy
  @Column(name = "created_by", updatable = false)
  private U createdBy;
  @CreatedDate
  @Column(name = "created_date", updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime createdAt;

  @LastModifiedBy
  @Column(name = "modified_by")
  private U lastModifiedBy;
  @LastModifiedDate
  @Column(name = "modified_date")
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime lastModifiedDate;


}
