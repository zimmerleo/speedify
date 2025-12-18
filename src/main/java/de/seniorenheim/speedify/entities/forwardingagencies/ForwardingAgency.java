package de.seniorenheim.speedify.entities.forwardingagencies;

import de.seniorenheim.speedify.entities.finance.BankAccount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "forwarding_agencies")
public class ForwardingAgency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Level level;

    @Column(nullable = false)
    private Integer xp = 0;

    @ManyToOne
    @JoinColumn(nullable = false)
    private LegalForm legalForm;

    @OneToMany
    @JoinColumn(nullable = false)
    private List<BankAccount> bankAccounts;

}