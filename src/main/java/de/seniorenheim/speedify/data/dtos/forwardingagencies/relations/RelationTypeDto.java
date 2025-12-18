package de.seniorenheim.speedify.data.dtos.forwardingagencies.relations;

import de.seniorenheim.speedify.data.entities.forwardingagencies.relations.RelationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link RelationType}
 */
@Value
@Builder
@AllArgsConstructor
public class RelationTypeDto implements Serializable {
    Long id;
    String name;
}