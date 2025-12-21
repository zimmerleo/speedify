package de.seniorenheim.speedify.data.entities.forwardingagencies.memberships.roles;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn
    private Role superior;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Career career;
}