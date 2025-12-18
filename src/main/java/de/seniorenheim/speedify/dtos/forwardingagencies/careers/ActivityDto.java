package de.seniorenheim.speedify.dtos.forwardingagencies.careers;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.forwardingagencies.careers.Activity}
 */
@Value
public class ActivityDto implements Serializable {
    Long id;
    String description;
    CareerDto career;
}