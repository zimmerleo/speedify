package de.seniorenheim.speedify.entities.forwardingagencies.relations;

import de.seniorenheim.speedify.entities.forwardingagencies.ForwardingAgency;
import de.seniorenheim.speedify.idclasses.RelationId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "relations")
@IdClass(RelationId.class)
public class Relation {

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private ForwardingAgency forwardingAgency_1;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private ForwardingAgency forwardingAgency_2;

    @ManyToOne
    @JoinColumn(nullable = false)
    private RelationType relationType;
}