package de.seniorenheim.speedify.data.entities.users;

import de.seniorenheim.speedify.data.entities.dlcs.DLC;
import de.seniorenheim.speedify.data.entities.finance.BankAccount;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private BankAccount bankAccount;

    @Column(nullable = false)
    @Builder.Default
    private Boolean administrator = false;

    @ManyToMany
    @JoinTable(
            name = "users_dlcs",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "dlc_id")
    )
    private List<DLC> dlcs;

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime creationDate = LocalDateTime.now();
}