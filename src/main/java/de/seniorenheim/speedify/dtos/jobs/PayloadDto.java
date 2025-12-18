package de.seniorenheim.speedify.dtos.jobs;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.jobs.Payload}
 */
@Value
public class PayloadDto implements Serializable {
    Long id;
    String description;
}