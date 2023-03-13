package com.ksi.healthcaresystem.bedservice.controller;

import com.ksi.healthcaresystem.bedservice.dto.BedDto;
import com.ksi.healthcaresystem.bedservice.service.BedService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/beds")
@RequiredArgsConstructor
public class BedServiceController {
  private final BedService bedService;

  /**
   * This API creates and adds new bed to the database
   *
   * @param bedDto new bed details
   * @return saved bed details
   */
  @PostMapping
  public ResponseEntity<BedDto> saveBed(@RequestBody @Valid BedDto bedDto) {
    BedDto savedBed = bedService.createBed(bedDto);
    return new ResponseEntity<>(savedBed, HttpStatus.CREATED);
  }

  /**
   * This API gets all saved beds from the database
   *
   * @return list of saved beds
   */
  @GetMapping
  public ResponseEntity<List<BedDto>> getAllBeds() {
    List<BedDto> bedDtoList = bedService.getAllBeds();
    return new ResponseEntity<>(bedDtoList, HttpStatus.OK);
  }

  /**
   * This API gets bed by ID
   *
   * @param bedId bed ID
   * @return bed details
   */
  @GetMapping("{id}")
  public ResponseEntity<BedDto> getBedById(@PathVariable("id") long bedId) {
    BedDto bedDto = bedService.getBedById(bedId);
    return new ResponseEntity<>(bedDto, HttpStatus.OK);
  }

  /**
   * This API gets bed by code
   *
   * @param bedCode bed code
   * @return bed details
   */
  @GetMapping("{code}")
  public ResponseEntity<BedDto> getBedByCode(@PathVariable("code") String bedCode) {
    BedDto bedDto = bedService.getBedByCode(bedCode);
    return new ResponseEntity<>(bedDto, HttpStatus.OK);
  }

  /**
   * This API updates bed details by bed code
   *
   * @param bedId bed code of the bed been updated
   * @param bedDto  bed details
   * @return updated bed
   */
  @PutMapping("{id}")
  public ResponseEntity<BedDto> updateBed(@PathVariable("id") Long bedId, @RequestBody @Valid BedDto bedDto) {
    bedDto.setId(bedId);
    BedDto updatedBed = bedService.updateBed(bedDto);
    return new ResponseEntity<>(updatedBed, HttpStatus.OK);
  }

  /**
   * This API deletes bed
   *
   * @param bedId bed ID
   * @return message of deletion
   */
  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteBed(@PathVariable("id") Long bedId) {
    bedService.deleteBed(bedId);
    return new ResponseEntity<>("Bed successfully deleted", HttpStatus.OK);
  }
}
