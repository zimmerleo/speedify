package de.seniorenheim.speedify.data.dtos.forwardingagencies.careers;

import de.seniorenheim.speedify.data.entities.forwardingagencies.careers.Activity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Activity}
 */
@Value
@Builder
@AllArgsConstructor
public class ActivityDto implements Serializable {
    Long id;
    String description;
    CareerDto career;
}