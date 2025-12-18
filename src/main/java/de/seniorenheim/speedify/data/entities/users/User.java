package de.seniorenheim.speedify.data.entities.users;

import de.seniorenheim.speedify.data.entities.finance.BankAccount;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private BankAccount bankAccount;

    @Column
    @Builder.Default
    private Boolean administrator = false;
}