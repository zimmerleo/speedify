package de.seniorenheim.speedify.dtos.forwardingagencies.ranks;

import de.seniorenheim.speedify.dtos.forwardingagencies.ForwardingAgencyDto;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.forwardingagencies.ranks.ForwardingAgency_Rank}
 */
@Value
public class ForwardingAgency_RankDto implements Serializable {
    ForwardingAgencyDto forwardingAgency;
    RankDto rank;
}