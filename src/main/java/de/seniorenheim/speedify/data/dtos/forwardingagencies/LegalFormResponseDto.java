package de.seniorenheim.speedify.data.dtos.forwardingagencies;

import de.seniorenheim.speedify.data.entities.forwardingagencies.LegalForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link LegalForm}
 */
@Value
@Builder
@AllArgsConstructor
public class LegalFormResponseDto implements Serializable {
    Long id;
    String name;
    BigDecimal capitalStock;
}