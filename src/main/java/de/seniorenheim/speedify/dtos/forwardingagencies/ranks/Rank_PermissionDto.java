package de.seniorenheim.speedify.dtos.forwardingagencies.ranks;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.forwardingagencies.ranks.Rank_Permission}
 */
@Value
public class Rank_PermissionDto implements Serializable {
    RankDto rank;
    PermissionDto permission;
}