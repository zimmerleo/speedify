package de.seniorenheim.speedify.data.entities.forwardingagencies.relations;

import de.seniorenheim.speedify.data.entities.forwardingagencies.ForwardingAgency;
import de.seniorenheim.speedify.data.idclasses.RelationId;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "relations")
@IdClass(RelationId.class)
public class Relation {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private ForwardingAgency forwardingAgency_1;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private ForwardingAgency forwardingAgency_2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private RelationType relationType;
}