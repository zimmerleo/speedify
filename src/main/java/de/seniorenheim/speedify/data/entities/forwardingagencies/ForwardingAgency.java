package de.seniorenheim.speedify.data.entities.forwardingagencies;

import de.seniorenheim.speedify.data.entities.finance.BankAccount;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

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

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^[A-Z]{3}$")
    private String code;

    @Column(length = 1000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Level level;

    @Column(nullable = false)
    @Builder.Default
    private Integer xp = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private LegalForm legalForm;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private BankAccount bankAccount;

}