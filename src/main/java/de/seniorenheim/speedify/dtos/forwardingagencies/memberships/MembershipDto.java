package de.seniorenheim.speedify.dtos.forwardingagencies.memberships;

import de.seniorenheim.speedify.dtos.forwardingagencies.ForwardingAgencyDto;
import de.seniorenheim.speedify.dtos.forwardingagencies.careers.CareerDto;
import de.seniorenheim.speedify.dtos.forwardingagencies.ranks.RankDto;
import de.seniorenheim.speedify.dtos.users.UserDto;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.forwardingagencies.memberships.Membership}
 */
@Value
public class MembershipDto implements Serializable {
    Long id;
    ForwardingAgencyDto forwardingAgency;
    UserDto user;
    LocalDate since;
    LocalDate until;
    CareerDto career;
    RankDto rank;
}