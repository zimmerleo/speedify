package de.seniorenheim.speedify.data.dtos.trucks;

import de.seniorenheim.speedify.data.entities.trucks.TruckType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link TruckType}
 */
@Value
@Builder
@AllArgsConstructor
public class TruckTypeDto implements Serializable {
    Long id;
    String name;
}