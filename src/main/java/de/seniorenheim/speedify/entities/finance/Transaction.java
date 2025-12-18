package de.seniorenheim.speedify.entities.finance;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn
    private BankAccount from;

    @ManyToOne
    @JoinColumn(nullable = false)
    private BankAccount to;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String purpose;

    @Column(nullable = false)
    private LocalDateTime processedAt;
}