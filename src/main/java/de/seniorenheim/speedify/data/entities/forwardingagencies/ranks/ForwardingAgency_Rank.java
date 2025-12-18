package de.seniorenheim.speedify.data.entities.forwardingagencies.ranks;

import de.seniorenheim.speedify.data.entities.forwardingagencies.ForwardingAgency;
import de.seniorenheim.speedify.data.idclasses.ForwardingAgency_Rank_Id;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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