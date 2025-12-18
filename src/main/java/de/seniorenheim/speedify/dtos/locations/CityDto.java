package de.seniorenheim.speedify.dtos.locations;

import de.seniorenheim.speedify.dtos.dlcs.DLCDto;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.locations.City}
 */
@Value
public class CityDto implements Serializable {
    Long id;
    String name;
    CountryDto locatedIn;
    DLCDto dlc;
}