package org.itacademy.exam5.repo;

import org.itacademy.exam5.dto.ParkingSpotDto;
import org.itacademy.exam5.entity.ParkingSpot;
import org.itacademy.exam5.enums.ParkingSpotType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingSpotRepo extends JpaRepository<ParkingSpot, Long> {
    ParkingSpot getParkingSpotBySpotNumber(String spotNumber);
    List<ParkingSpot> getParkingSpotsByType(ParkingSpotType type);



}
