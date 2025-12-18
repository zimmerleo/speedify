package de.seniorenheim.speedify.dtos.forwardingagencies;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.forwardingagencies.LegalForm}
 */
@Value
public class LegalFormDto implements Serializable {
    Long id;
    String name;
    Double capitalStock;
}