package de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships;

import de.seniorenheim.speedify.data.entities.forwardingagencies.memberships.Application;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Application}
 */
@Value
@Builder
@AllArgsConstructor
public class ApplicationCreationDto implements Serializable {
    Long role;
    String text;
}