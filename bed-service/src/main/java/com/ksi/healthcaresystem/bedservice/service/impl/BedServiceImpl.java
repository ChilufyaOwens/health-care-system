package com.ksi.healthcaresystem.bedservice.service.impl;

import com.ksi.healthcaresystem.bedservice.dto.BedDto;
import com.ksi.healthcaresystem.bedservice.dto.constants.BedType;
import com.ksi.healthcaresystem.bedservice.entity.Bed;
import com.ksi.healthcaresystem.bedservice.exception.BedCodeAlreadyExistsException;
import com.ksi.healthcaresystem.bedservice.mapper.BedMapper;
import com.ksi.healthcaresystem.bedservice.repository.BedRepository;
import com.ksi.healthcaresystem.bedservice.service.BedService;
import com.ksi.healthcaresystem.commons.constants.Status;
import com.ksi.healthcaresystem.commons.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "BED_SERVICE")
public class BedServiceImpl implements BedService {
  private final BedRepository bedRepository;

  /**
   * This method creates bed and saves to the database
   *
   * @param bedDto bed details
   * @return saved bed details
   */
  @Override
  public BedDto createBed(BedDto bedDto) {
    log.info("Creating new Bed with details, code: {}, type {}", bedDto.getBedCode(), bedDto.getBedType());

    //Check if the bed with code already exist
    Optional<Bed> optionalBed = bedRepository.findBedByBedCode(bedDto.getBedCode());
    if (optionalBed.isPresent()) {
      throw new BedCodeAlreadyExistsException("The supplied Bed code already exists");
    }
    Bed bed = BedMapper.MAPPER.mapToBed(bedDto);
    bed.setStatus(Status.AVAILABLE);
    Bed savedBed = bedRepository.save(bed);

    return BedMapper.MAPPER.mapToBedDto(savedBed);
  }

  /**
   * This method gets bed by bed code
   *
   * @param bedCode bed code
   * @return bed details
   */
  @Override
  public BedDto getBedByCode(String bedCode) {
    log.info("Fetching Bed details with bed code: {}", bedCode);
    Optional<Bed> optionalBed = bedRepository.findBedByBedCode(bedCode);
    if (optionalBed.isEmpty()) {
      throw new ResourceNotFoundException("Bed", "code", bedCode);
    }
    Bed bed = optionalBed.get();
    return BedMapper.MAPPER.mapToBedDto(bed);
  }

  /**
   * This method gets bed by ID
   *
   * @param bedId bed Id
   * @return bed details
   */
  @Override
  public BedDto getBedById(Long bedId) {
    log.info("Fetching Bed details with Bed Id: {}", bedId);

    Optional<Bed> optionalBed = bedRepository.findById(bedId);
    if (optionalBed.isEmpty()) {
      throw new ResourceNotFoundException("Bed", "Id", String.valueOf(bedId));
    }
    Bed bed = optionalBed.get();
    return BedMapper.MAPPER.mapToBedDto(bed);
  }

  /**
   * This method gets a list of all saved beds from the database
   *
   * @return list of beds
   */
  @Override
  public List<BedDto> getAllBeds() {
    log.info("Fetching all Beds saved in the database");
    List<Bed> beds = bedRepository.findAll();

    return beds.stream()
        .map(BedMapper.MAPPER::mapToBedDto)
        .toList();
  }

  /**
   * This method updates bed details
   * @param bedDto bed details
   * @return updated bed
   */
  @Override
  public BedDto updateBed(BedDto bedDto) {
    log.info("Updating Bed details with bed Id: {}", bedDto.getId());
    Bed existingBed = bedRepository.findById(bedDto.getId())
        .orElseThrow(() -> new ResourceNotFoundException("Bed",
            "id", String.valueOf(bedDto.getId())));

    existingBed.setBedType(BedType.valueOf(bedDto.getBedType()));
    existingBed.setStatus(Status.valueOf(bedDto.getStatus()));
    existingBed.setBedCode(bedDto.getBedCode());

    //Save updated bed
    Bed updatedBed = bedRepository.save(existingBed);
    return BedMapper.MAPPER.mapToBedDto(updatedBed);
  }

  /**
   * This method deletes by bed ID
   * @param bedId bed Id
   */
  @Override
  public void deleteBed(Long bedId) {
    log.info("Deleting Bed with Id: {}", bedId);
    bedRepository.deleteById(bedId);
  }
}
