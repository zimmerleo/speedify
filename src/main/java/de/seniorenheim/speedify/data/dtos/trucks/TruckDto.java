package de.seniorenheim.speedify.data.dtos.trucks;

import de.seniorenheim.speedify.data.entities.trucks.Truck;
import de.seniorenheim.speedify.data.dtos.users.UserDto;
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
public class TruckDto implements Serializable {
    Long id;
    String licensePlate;
    TruckTypeDto type;
    UserDto owner;
}