package de.seniorenheim.speedify.data.dtos.forwardingagencies;

import de.seniorenheim.speedify.data.entities.forwardingagencies.ForwardingAgency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ForwardingAgency}
 */
@Value
@Builder
@AllArgsConstructor
public class ForwardingAgencyCreationDto implements Serializable {
    String name;
    String description;
    String code;
    Long legalForm;
}