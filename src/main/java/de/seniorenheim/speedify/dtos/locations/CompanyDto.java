package de.seniorenheim.speedify.dtos.locations;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.locations.Company}
 */
@Value
public class CompanyDto implements Serializable {
    Long id;
    String name;
    CityDto locatedIn;
}