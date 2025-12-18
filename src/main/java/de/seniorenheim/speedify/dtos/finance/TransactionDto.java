package de.seniorenheim.speedify.dtos.finance;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.finance.Transaction}
 */
@Value
public class TransactionDto implements Serializable {
    Long id;
    BankAccountDto from;
    BankAccountDto to;
    double amount;
    String purpose;
    LocalDateTime processedAt;
}