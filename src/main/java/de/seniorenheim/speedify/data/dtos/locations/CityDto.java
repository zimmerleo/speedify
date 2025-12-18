package de.seniorenheim.speedify.data.dtos.locations;

import de.seniorenheim.speedify.data.dtos.dlcs.DLCDto;
import de.seniorenheim.speedify.data.entities.locations.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link City}
 */
@Value
@Builder
@AllArgsConstructor
public class CityDto implements Serializable {
    Long id;
    String name;
    CountryDto locatedIn;
    DLCDto dlc;
}