package de.seniorenheim.speedify.data.entities.forwardingagencies.memberships;

import de.seniorenheim.speedify.data.entities.forwardingagencies.ForwardingAgency;
import de.seniorenheim.speedify.data.entities.forwardingagencies.memberships.roles.Role;
import de.seniorenheim.speedify.data.entities.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "memberships")
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private ForwardingAgency forwardingAgency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate since;

    @Builder.Default
    private LocalDate until = null;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Role role;
}