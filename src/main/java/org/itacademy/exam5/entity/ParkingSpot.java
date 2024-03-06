package org.itacademy.exam5.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.itacademy.exam5.enums.ParkingSpotStatus;
import org.itacademy.exam5.enums.ParkingSpotType;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String spotNumber;
    private ParkingSpotType type;
    private ParkingSpotStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;






}
