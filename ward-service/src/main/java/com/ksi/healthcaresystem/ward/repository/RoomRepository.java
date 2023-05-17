package com.ksi.healthcaresystem.ward.repository;

import com.ksi.healthcaresystem.ward.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
