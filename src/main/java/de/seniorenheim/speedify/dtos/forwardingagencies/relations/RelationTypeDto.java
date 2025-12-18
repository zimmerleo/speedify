package de.seniorenheim.speedify.dtos.forwardingagencies.relations;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.forwardingagencies.relations.RelationType}
 */
@Value
public class RelationTypeDto implements Serializable {
    Long id;
    String name;
}