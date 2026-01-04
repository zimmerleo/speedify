package de.seniorenheim.speedify.data.dtos.users;

import de.seniorenheim.speedify.data.entities.users.Rank;
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
public class RankResponseDto implements Serializable {
    Long id;
    String name;
    RankResponseDto superior;
    Integer xpNeeded;
}