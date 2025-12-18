package de.seniorenheim.speedify.dtos.finance;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.finance.BankAccount}
 */
@Value
public class BankAccountDto implements Serializable {
    String iban;
    Double balance;
}