package org.itacademy.exam5.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.itacademy.exam5.enums.ParkingSpotStatus;
import org.itacademy.exam5.enums.ParkingSpotType;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingSpotDto {
    private Long id;
    private String spotNumber;
    private ParkingSpotType type;
    private ParkingSpotStatus status;
    private Long userId;

}
