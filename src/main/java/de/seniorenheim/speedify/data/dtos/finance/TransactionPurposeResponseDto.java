package de.seniorenheim.speedify.data.dtos.finance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.data.entities.finance.TransactionPurpose}
 */
@Value
@Builder
@AllArgsConstructor
public class TransactionPurposeResponseDto implements Serializable {
    Long id;
    String purpose;
}