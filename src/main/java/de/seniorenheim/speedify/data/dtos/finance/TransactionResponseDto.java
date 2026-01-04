package de.seniorenheim.speedify.data.dtos.finance;

import de.seniorenheim.speedify.data.entities.finance.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
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
    BigDecimal amount;
    String purpose;
    LocalDateTime processedAt;
}