package de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships;

import de.seniorenheim.speedify.data.dtos.forwardingagencies.ForwardingAgencyDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.careers.CareerDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.ranks.RankDto;
import de.seniorenheim.speedify.data.dtos.users.UserDto;
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
public class ApplicationDto implements Serializable {
    Long id;
    ForwardingAgencyDto forwardingAgency;
    UserDto user;
    CareerDto career;
    RankDto rank;
    String text;
}