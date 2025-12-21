package de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships.roles;

import de.seniorenheim.speedify.data.entities.forwardingagencies.memberships.roles.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Role}
 */
@Value
@Builder
@AllArgsConstructor
public class RoleResponseDto implements Serializable {
    Long id;
    String name;
    RoleResponseDto superior;
    CareerResponseDto career;
}