package de.seniorenheim.speedify.data.dtos.forwardingagencies.relations;

import de.seniorenheim.speedify.data.entities.forwardingagencies.relations.Relation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Relation}
 */
@Value
@Builder
@AllArgsConstructor
public class RelationCreationDto implements Serializable {
    Long forwardingAgency_2;
    Long relationType;
}