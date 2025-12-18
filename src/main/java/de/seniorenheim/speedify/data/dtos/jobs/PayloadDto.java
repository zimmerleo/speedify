package de.seniorenheim.speedify.data.dtos.jobs;

import de.seniorenheim.speedify.data.entities.jobs.Payload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Payload}
 */
@Value
@Builder
@AllArgsConstructor
public class PayloadDto implements Serializable {
    Long id;
    String description;
}