package de.seniorenheim.speedify.data.dtos.forwardingagencies;

import de.seniorenheim.speedify.data.entities.forwardingagencies.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Level}
 */
@Value
@Builder
@AllArgsConstructor
public class LevelDto implements Serializable {
    Long id;
    String name;
    String description;
}