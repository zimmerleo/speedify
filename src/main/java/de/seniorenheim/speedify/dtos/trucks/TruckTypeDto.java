package de.seniorenheim.speedify.dtos.trucks;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.trucks.TruckType}
 */
@Value
public class TruckTypeDto implements Serializable {
    Long id;
    String name;
}