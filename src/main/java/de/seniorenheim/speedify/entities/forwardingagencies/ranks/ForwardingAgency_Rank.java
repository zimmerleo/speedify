package de.seniorenheim.speedify.entities.forwardingagencies.ranks;

import de.seniorenheim.speedify.entities.forwardingagencies.ForwardingAgency;
import de.seniorenheim.speedify.idclasses.ForwardingAgency_Rank_Id;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "forwarding_agency_ranks")
@IdClass(ForwardingAgency_Rank_Id.class)
public class ForwardingAgency_Rank {
    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private ForwardingAgency forwardingAgency;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Rank rank;
}