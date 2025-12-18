package de.seniorenheim.speedify.data.dtos.forwardingagencies.careers;

import de.seniorenheim.speedify.data.dtos.forwardingagencies.ForwardingAgencyDto;
import de.seniorenheim.speedify.data.entities.forwardingagencies.careers.ForwardingAgency_Career;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ForwardingAgency_Career}
 */
@Value
@Builder
@AllArgsConstructor
public class ForwardingAgency_CareerDto implements Serializable {
    ForwardingAgencyDto forwardingAgency;
    CareerDto career;
}