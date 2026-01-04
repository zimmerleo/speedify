package de.seniorenheim.speedify.data.dtos.finance;

import de.seniorenheim.speedify.data.entities.finance.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link BankAccount}
 */
@Value
@Builder
@AllArgsConstructor
public class BankAccountResponseDto implements Serializable {
    String iban;
    BigDecimal balance;
}