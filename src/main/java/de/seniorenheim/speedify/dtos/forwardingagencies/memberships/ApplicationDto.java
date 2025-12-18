package de.seniorenheim.speedify.dtos.forwardingagencies.memberships;

import de.seniorenheim.speedify.dtos.forwardingagencies.ForwardingAgencyDto;
import de.seniorenheim.speedify.dtos.forwardingagencies.careers.CareerDto;
import de.seniorenheim.speedify.dtos.forwardingagencies.ranks.RankDto;
import de.seniorenheim.speedify.dtos.users.UserDto;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.forwardingagencies.memberships.Application}
 */
@Value
public class ApplicationDto implements Serializable {
    Long id;
    ForwardingAgencyDto forwardingAgency;
    UserDto user;
    CareerDto career;
    RankDto rank;
    String text;
}