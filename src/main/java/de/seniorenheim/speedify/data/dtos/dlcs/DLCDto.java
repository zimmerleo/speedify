package de.seniorenheim.speedify.data.dtos.dlcs;

import de.seniorenheim.speedify.data.entities.dlcs.DLC;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link DLC}
 */
@Value
@Builder
@AllArgsConstructor
public class DLCDto implements Serializable {
    Long id;
    String name;
}