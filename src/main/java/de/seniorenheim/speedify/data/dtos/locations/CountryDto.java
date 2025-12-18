package de.seniorenheim.speedify.data.dtos.locations;

import de.seniorenheim.speedify.data.entities.locations.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Country}
 */
@Value
@Builder
@AllArgsConstructor
public class CountryDto implements Serializable {
    Long id;
    String name;
}