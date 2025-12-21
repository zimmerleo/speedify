package de.seniorenheim.speedify.data.entities.forwardingagencies;

import de.seniorenheim.speedify.data.entities.finance.BankAccount;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "forwarding_agencies")
public class ForwardingAgency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Level level;

    @Column(nullable = false)
    @Builder.Default
    private Integer xp = 0;

    @ManyToOne
    @JoinColumn(nullable = false)
    private LegalForm legalForm;

    @OneToMany
    @JoinColumn(nullable = false)
    private List<BankAccount> bankAccounts;

}