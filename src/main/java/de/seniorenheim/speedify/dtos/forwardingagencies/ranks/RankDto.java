package de.seniorenheim.speedify.dtos.forwardingagencies.ranks;

import de.seniorenheim.speedify.entities.forwardingagencies.ranks.Rank;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.forwardingagencies.ranks.Rank}
 */
@Value
public class RankDto implements Serializable {
    Long id;
    String name;
    RankDto superior;
    RankDto subordinate;
}