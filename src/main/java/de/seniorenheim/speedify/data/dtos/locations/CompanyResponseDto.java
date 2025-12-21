package de.seniorenheim.speedify.data.dtos.locations;

import de.seniorenheim.speedify.data.entities.locations.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Company}
 */
@Value
@Builder
@AllArgsConstructor
public class CompanyResponseDto implements Serializable {
    Long id;
    String name;
}