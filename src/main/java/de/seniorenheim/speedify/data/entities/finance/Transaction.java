package de.seniorenheim.speedify.data.entities.finance;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private BankAccount payer;

    @ManyToOne
    @JoinColumn
    private BankAccount payee;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String purpose;

    @Column(nullable = false)
    private LocalDateTime processedAt;
}