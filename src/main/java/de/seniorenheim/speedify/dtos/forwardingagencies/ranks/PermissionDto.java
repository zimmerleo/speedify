package de.seniorenheim.speedify.dtos.forwardingagencies.ranks;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.forwardingagencies.ranks.Permission}
 */
@Value
public class PermissionDto implements Serializable {
    Long id;
    String description;
}