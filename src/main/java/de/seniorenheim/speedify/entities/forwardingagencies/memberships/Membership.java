package de.seniorenheim.speedify.entities.forwardingagencies.memberships;

import de.seniorenheim.speedify.entities.forwardingagencies.ranks.Rank;
import de.seniorenheim.speedify.entities.users.User;
import de.seniorenheim.speedify.entities.forwardingagencies.careers.Career;
import de.seniorenheim.speedify.entities.forwardingagencies.ForwardingAgency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "memberships")
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ForwardingAgency forwardingAgency;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate since;

    private LocalDate until;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Career career;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Rank rank;

}