package de.seniorenheim.speedify.data.dtos.forwardingagencies.relations;

import de.seniorenheim.speedify.data.dtos.forwardingagencies.ForwardingAgencyResponseDto;
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
public class RelationResponseDto implements Serializable {
    ForwardingAgencyResponseDto forwardingAgency_1;
    ForwardingAgencyResponseDto forwardingAgency_2;
    RelationTypeResponseDto relationType;
}