package de.seniorenheim.speedify.data.dtos.trucks;

import de.seniorenheim.speedify.data.dtos.users.UserResponseDto;
import de.seniorenheim.speedify.data.entities.trucks.Truck;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Truck}
 */
@Value
@Builder
@AllArgsConstructor
public class TruckResponseDto implements Serializable {
    Long id;
    String licensePlate;
    TruckTypeResponseDto type;
    UserResponseDto owner;
}