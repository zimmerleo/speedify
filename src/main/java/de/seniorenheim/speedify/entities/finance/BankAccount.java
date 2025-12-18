package de.seniorenheim.speedify.entities.finance;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bank_accounts")
public class BankAccount {
    @Id
    @Column(nullable = false, length = 22)
    private String iban;

    @Column(nullable = false)
    private Double balance;

}