package de.seniorenheim.speedify.data.entities.forwardingagencies.memberships;

import de.seniorenheim.speedify.data.entities.forwardingagencies.ranks.Rank;
import de.seniorenheim.speedify.data.entities.users.User;
import de.seniorenheim.speedify.data.entities.forwardingagencies.careers.Career;
import de.seniorenheim.speedify.data.entities.forwardingagencies.ForwardingAgency;
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