package com.ksi.healthcaresystem.registration.repository;

import com.ksi.healthcaresystem.registration.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}