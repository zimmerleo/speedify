package de.seniorenheim.speedify.data.dtos.forwardingagencies.relations;

import de.seniorenheim.speedify.data.entities.forwardingagencies.relations.Relation;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.ForwardingAgencyDto;
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
public class RelationDto implements Serializable {
    ForwardingAgencyDto forwardingAgency_1;
    ForwardingAgencyDto forwardingAgency_2;
    RelationTypeDto relationType;
}