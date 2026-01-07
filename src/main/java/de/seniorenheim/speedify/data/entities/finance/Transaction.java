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

    @ManyToOne
    @JoinColumn
    private BankAccount payer;

    @ManyToOne
    @JoinColumn
    private BankAccount payee;

    @Column(scale = 2, nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TransactionPurpose purpose;

    @ManyToOne
    @JoinColumn
    private Job job;

    @Column(nullable = false)
    private LocalDateTime processedAt;
}