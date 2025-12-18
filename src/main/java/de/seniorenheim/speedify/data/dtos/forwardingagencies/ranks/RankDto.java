package de.seniorenheim.speedify.data.dtos.forwardingagencies.ranks;

import de.seniorenheim.speedify.data.entities.forwardingagencies.ranks.Rank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Rank}
 */
@Value
@Builder
@AllArgsConstructor
public class RankDto implements Serializable {
    Long id;
    String name;
    RankDto superior;
    RankDto subordinate;
}