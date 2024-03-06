package org.itacademy.exam5.service;

import org.itacademy.exam5.dto.ParkingDto;
import org.itacademy.exam5.dto.ParkingSpotDto;
import org.itacademy.exam5.enums.ParkingSpotType;

import java.util.List;
import java.util.Optional;

public interface ParkingSpotService {
    ParkingSpotDto create(ParkingSpotDto dto);
    List<ParkingSpotDto> getAll();
    ParkingSpotDto getParkingSpotBySpotNumber(String spotNumber);
    ParkingSpotDto update(Long id, ParkingSpotDto dto);
    void delete(Long id);

    void book(ParkingDto dto);
    void release(ParkingDto dto);
    List<ParkingSpotDto> getByType(ParkingSpotType type);
}
