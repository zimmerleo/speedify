package de.seniorenheim.speedify.dtos.forwardingagencies.careers;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.forwardingagencies.careers.Career}
 */
@Value
public class CareerDto implements Serializable {
    Long id;
    String name;
}