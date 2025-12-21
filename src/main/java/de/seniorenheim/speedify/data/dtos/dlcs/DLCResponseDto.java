package de.seniorenheim.speedify.data.dtos.dlcs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.data.entities.dlcs.DLC}
 */
@Value
@Builder
@AllArgsConstructor
public class DLCResponseDto implements Serializable {
    Long id;
    String name;
}