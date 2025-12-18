package de.seniorenheim.speedify.data.dtos.forwardingagencies.careers;

import de.seniorenheim.speedify.data.entities.forwardingagencies.careers.Career;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Career}
 */
@Value
@Builder
@AllArgsConstructor
public class CareerDto implements Serializable {
    Long id;
    String name;
}