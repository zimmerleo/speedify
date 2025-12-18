package de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships;

import de.seniorenheim.speedify.data.dtos.forwardingagencies.ForwardingAgencyDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.careers.CareerDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.ranks.RankDto;
import de.seniorenheim.speedify.data.dtos.users.UserDto;
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
public class MembershipDto implements Serializable {
    Long id;
    ForwardingAgencyDto forwardingAgency;
    UserDto user;
    LocalDate since;
    LocalDate until;
    CareerDto career;
    RankDto rank;
}