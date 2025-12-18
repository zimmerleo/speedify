package de.seniorenheim.speedify.dtos.locations;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.locations.Country}
 */
@Value
public class CountryDto implements Serializable {
    Long id;
    String name;
}