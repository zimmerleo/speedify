package de.seniorenheim.speedify.dtos.forwardingagencies.careers;

import de.seniorenheim.speedify.dtos.forwardingagencies.ForwardingAgencyDto;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.forwardingagencies.careers.ForwardingAgency_Career}
 */
@Value
public class ForwardingAgency_CareerDto implements Serializable {
    ForwardingAgencyDto forwardingAgency;
    CareerDto career;
}