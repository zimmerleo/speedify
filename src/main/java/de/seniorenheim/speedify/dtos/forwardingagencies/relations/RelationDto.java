package de.seniorenheim.speedify.dtos.forwardingagencies.relations;

import de.seniorenheim.speedify.dtos.forwardingagencies.ForwardingAgencyDto;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.forwardingagencies.relations.Relation}
 */
@Value
public class RelationDto implements Serializable {
    ForwardingAgencyDto forwardingAgency_1;
    ForwardingAgencyDto forwardingAgency_2;
    RelationTypeDto relationType;
}