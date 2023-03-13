package com.ksi.healthcaresystem.bedservice.service;

import com.ksi.healthcaresystem.bedservice.dto.BedDto;
import java.util.List;

public interface BedService {
  BedDto createBed(BedDto bedDto);
  BedDto getBedByCode(String bedCode);
  BedDto getBedById(Long bedId);
  List<BedDto> getAllBeds();
  BedDto updateBed(BedDto bedDto);
  void deleteBed(Long bedId);
}
