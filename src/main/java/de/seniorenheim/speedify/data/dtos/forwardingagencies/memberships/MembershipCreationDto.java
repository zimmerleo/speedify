package de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships;

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
public class MembershipCreationDto implements Serializable {
    Long forwardingAgency;
    Long user;
    LocalDate since;
    LocalDate until;
    Long role;
}