package de.seniorenheim.speedify.data.dtos.forwardingagencies.ranks;

import de.seniorenheim.speedify.data.entities.forwardingagencies.ranks.Rank_Permission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Rank_Permission}
 */
@Value
@Builder
@AllArgsConstructor
public class Rank_PermissionDto implements Serializable {
    RankDto rank;
    PermissionDto permission;
}