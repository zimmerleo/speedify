package de.seniorenheim.speedify.dtos.trucks;

import de.seniorenheim.speedify.dtos.users.UserDto;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.trucks.Truck}
 */
@Value
public class TruckDto implements Serializable {
    Long id;
    String licensePlate;
    TruckTypeDto type;
    UserDto owner;
}