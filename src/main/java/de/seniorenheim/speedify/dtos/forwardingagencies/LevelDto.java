package de.seniorenheim.speedify.dtos.forwardingagencies;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.forwardingagencies.Level}
 */
@Value
public class LevelDto implements Serializable {
    Long id;
    String name;
    String description;
}