package de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships.roles;

import de.seniorenheim.speedify.data.entities.forwardingagencies.memberships.roles.Career;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Career}
 */
@Value
@Builder
@AllArgsConstructor
public class CareerResponseDto implements Serializable {
    Long id;
    String name;
    String description;
}