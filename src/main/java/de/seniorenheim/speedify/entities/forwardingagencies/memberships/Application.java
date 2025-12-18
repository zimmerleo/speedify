package de.seniorenheim.speedify.entities.forwardingagencies.memberships;

import de.seniorenheim.speedify.entities.forwardingagencies.ranks.Rank;
import de.seniorenheim.speedify.entities.users.User;
import de.seniorenheim.speedify.entities.forwardingagencies.careers.Career;
import de.seniorenheim.speedify.entities.forwardingagencies.ForwardingAgency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "applications")
public class Application {
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

    @ManyToOne
    @JoinColumn(nullable = false)
    private Career career;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Rank rank;

    @Column(length = 1000)
    private String text;

}