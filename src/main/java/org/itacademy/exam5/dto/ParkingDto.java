package org.itacademy.exam5.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingDto {
    private Long id;
    private String spotNumber;
    private Long userId;

}
