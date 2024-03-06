package org.itacademy.exam5.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.itacademy.exam5.dto.ParkingDto;
import org.itacademy.exam5.dto.ParkingSpotDto;
import org.itacademy.exam5.entity.ParkingSpot;
import org.itacademy.exam5.entity.User;
import org.itacademy.exam5.enums.ParkingSpotStatus;
import org.itacademy.exam5.enums.ParkingSpotType;
import org.itacademy.exam5.repo.ParkingSpotRepo;
import org.itacademy.exam5.service.ParkingSpotService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingSpotServiceImpl implements ParkingSpotService {
    private final ParkingSpotRepo repo;

    @Override
    public ParkingSpotDto create(ParkingSpotDto dto) {
        ParkingSpot spot = ParkingSpot.builder()
                .spotNumber(dto.getSpotNumber())
                .type(ParkingSpotType.valueOfIgnoreCase(dto.getType().name()))
                .status(ParkingSpotStatus.FREE)
                .build();

        dto.setStatus(ParkingSpotStatus.FREE);
        repo.save(spot);
        return dto;
    }

    @Override
    public List<ParkingSpotDto> getAll() {
        List<ParkingSpot> spots = repo.findAll();
        List<ParkingSpotDto> dtoList = new ArrayList<>();
        for (ParkingSpot spot: spots){
            ParkingSpotDto dto = ParkingSpotDto.builder()
                    .id(spot.getId())
                    .spotNumber(spot.getSpotNumber())
                    .status(spot.getStatus())
                    .type(spot.getType())
                    .build();
            dtoList.add(dto);
        }
        return dtoList;
    }
    @Override
    public ParkingSpotDto getParkingSpotBySpotNumber(String spotNumber) {
        ParkingSpot spot = repo.getParkingSpotBySpotNumber(spotNumber);
        ParkingSpotDto dto = ParkingSpotDto.builder()
                .id(spot.getId())
                .spotNumber(spot.getSpotNumber())
                .status(spot.getStatus())
                .type(spot.getType())
                .build();
        return dto;
    }

    @Override
    public ParkingSpotDto update(Long id, ParkingSpotDto dto) {
        ParkingSpot spot = repo.findById(id).orElseThrow(()-> new EntityNotFoundException("Парковочное место с ID " + id + " не найдено"));
        if (dto.getSpotNumber() != null)
            spot.setSpotNumber(dto.getSpotNumber());
        if (dto.getType() != null)
            spot.setType(dto.getType());
        repo.save(spot);
        return dto;
    }

    @Override
    public void delete(Long id) {
        ParkingSpot spot = repo.findById(id).orElseThrow(()-> new EntityNotFoundException("Парковочное место с ID " + id + " не найдено"));
        repo.delete(spot);
    }

    @Override
    public void book(ParkingDto dto) {
        ParkingSpot spot = repo.getParkingSpotBySpotNumber(dto.getSpotNumber());
        if (spot != null && spot.getStatus().equals(ParkingSpotStatus.FREE)) {
            spot.setStatus(ParkingSpotStatus.OCCUPIED);
            spot.setUser(new User(dto.getId()));
            repo.save(spot);
        } else {
            throw new IllegalArgumentException("Парковочное место уже занято");
        }
    }


    @Override
    public void release(ParkingDto dto) {
        ParkingSpot spot = repo.getParkingSpotBySpotNumber(dto.getSpotNumber());

        if (spot != null && spot.getStatus().equals(ParkingSpotStatus.OCCUPIED)) {
            spot.setStatus(ParkingSpotStatus.FREE);
            spot.setUser(null);
            repo.save(spot);
        } else {
            throw new IllegalArgumentException("Парковочное место уже свободно");
        }
    }

    @Override
    public List<ParkingSpotDto> getByType (ParkingSpotType type) {
        List<ParkingSpot> spots = repo.getParkingSpotsByType(type);
        List<ParkingSpotDto> dtoList = new ArrayList<>();
        for (ParkingSpot spot: spots){
            ParkingSpotDto dto = ParkingSpotDto.builder()
                    .id(spot.getId())
                    .spotNumber(spot.getSpotNumber())
                    .status(spot.getStatus())
                    .type(spot.getType())
                    .build();
            dtoList.add(dto);
        }
        return dtoList;
    }
}
