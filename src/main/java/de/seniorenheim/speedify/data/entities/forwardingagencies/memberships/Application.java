package de.seniorenheim.speedify.data.entities.forwardingagencies.memberships;

import de.seniorenheim.speedify.data.entities.forwardingagencies.ForwardingAgency;
import de.seniorenheim.speedify.data.entities.forwardingagencies.memberships.roles.Role;
import de.seniorenheim.speedify.data.entities.users.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private ForwardingAgency forwardingAgency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Role role;

    @Column(length = 1000)
    private String text;
}