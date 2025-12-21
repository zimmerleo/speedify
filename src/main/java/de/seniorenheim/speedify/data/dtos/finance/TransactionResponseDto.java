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
public class TransactionResponseDto implements Serializable {
    Long id;
    BankAccountResponseDto payer;
    BankAccountResponseDto payee;
    double amount;
    String purpose;
    LocalDateTime processedAt;
}