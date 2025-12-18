package de.seniorenheim.speedify.dtos.dlcs;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.dlcs.DLC}
 */
@Value
public class DLCDto implements Serializable {
    Long id;
    String name;
}