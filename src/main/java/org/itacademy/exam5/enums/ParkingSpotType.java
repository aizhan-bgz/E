package org.itacademy.exam5.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParkingSpotType {
    STANDARD ("Стандартный"),
    DISABLED("Для инвалидов"),
    FAMILY("Для семей с детьми"),
    ELECTRIC_VEHICLES("Для электромобилей")
    ;
    String description;

    public static ParkingSpotType valueOfIgnoreCase(String value) {
        for (ParkingSpotType type : values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant " + ParkingSpotType.class + "." + value);
    }
}