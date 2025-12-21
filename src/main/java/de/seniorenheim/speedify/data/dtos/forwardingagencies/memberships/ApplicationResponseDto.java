package de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships;

import de.seniorenheim.speedify.data.dtos.forwardingagencies.ForwardingAgencyResponseDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships.roles.RoleResponseDto;
import de.seniorenheim.speedify.data.dtos.users.UserResponseDto;
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
public class ApplicationResponseDto implements Serializable {
    Long id;
    ForwardingAgencyResponseDto forwardingAgency;
    UserResponseDto user;
    RoleResponseDto role;
    String text;
}