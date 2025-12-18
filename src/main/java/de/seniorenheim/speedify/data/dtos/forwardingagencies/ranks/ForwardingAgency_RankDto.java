package de.seniorenheim.speedify.data.dtos.forwardingagencies.ranks;

import de.seniorenheim.speedify.data.entities.forwardingagencies.ranks.ForwardingAgency_Rank;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.ForwardingAgencyDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ForwardingAgency_Rank}
 */
@Value
@Builder
@AllArgsConstructor
public class ForwardingAgency_RankDto implements Serializable {
    ForwardingAgencyDto forwardingAgency;
    RankDto rank;
}