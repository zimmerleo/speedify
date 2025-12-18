package de.seniorenheim.speedify.data.dtos.forwardingagencies.ranks;

import de.seniorenheim.speedify.data.entities.forwardingagencies.ranks.Permission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Permission}
 */
@Value
@Builder
@AllArgsConstructor
public class PermissionDto implements Serializable {
    Long id;
    String description;
}