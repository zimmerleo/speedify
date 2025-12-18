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