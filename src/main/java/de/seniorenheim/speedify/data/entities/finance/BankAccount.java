package de.seniorenheim.speedify.data.entities.finance;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank_accounts")
public class BankAccount {

    @Id
    @Pattern(regexp = "ET52\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d", message = "Invalid IBAN")
    @Column(nullable = false, length = 22)
    private String iban;

    @Column(nullable = false)
    @Builder.Default
    private Double balance = 0D;
}