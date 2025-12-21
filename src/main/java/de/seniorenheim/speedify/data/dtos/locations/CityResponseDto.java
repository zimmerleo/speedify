package de.seniorenheim.speedify.data.dtos.locations;

import de.seniorenheim.speedify.data.dtos.dlcs.DLCResponseDto;
import de.seniorenheim.speedify.data.entities.locations.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link City}
 */
@Value
@Builder
@AllArgsConstructor
public class CityResponseDto implements Serializable {
    Long id;
    String name;
    CountryResponseDto locatedIn;
    DLCResponseDto dlc;
    List<CompanyResponseDto> companies;
}