package de.seniorenheim.speedify.data.dtos.finance;

import de.seniorenheim.speedify.data.entities.finance.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Transaction}
 */
@Value
@Builder
@AllArgsConstructor
public class TransactionDto implements Serializable {
    Long id;
    BankAccountDto from;
    BankAccountDto to;
    double amount;
    String purpose;
    LocalDateTime processedAt;
}