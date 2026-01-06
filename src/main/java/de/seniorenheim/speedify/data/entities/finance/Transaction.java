package de.seniorenheim.speedify.data.entities.finance;

import de.seniorenheim.speedify.data.entities.jobs.Job;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private BankAccount payer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private BankAccount payee;

    @Column(scale = 2, nullable = false)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private TransactionPurpose purpose;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Job job;

    @Column(nullable = false)
    private LocalDateTime processedAt;
}