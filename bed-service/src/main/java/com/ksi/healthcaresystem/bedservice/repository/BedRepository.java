package com.ksi.healthcaresystem.bedservice.repository;

import com.ksi.healthcaresystem.bedservice.entity.Bed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BedRepository extends JpaRepository<Bed, Long> {

}
