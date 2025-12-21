package de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships;

import de.seniorenheim.speedify.data.dtos.forwardingagencies.ForwardingAgencyResponseDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships.roles.RoleResponseDto;
import de.seniorenheim.speedify.data.dtos.users.UserResponseDto;
import de.seniorenheim.speedify.data.entities.forwardingagencies.memberships.Membership;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Membership}
 */
@Value
@Builder
@AllArgsConstructor
public class MembershipResponseDto implements Serializable {
    Long id;
    ForwardingAgencyResponseDto forwardingAgency;
    UserResponseDto user;
    LocalDate since;
    LocalDate until;
    RoleResponseDto role;
}