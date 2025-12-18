package de.seniorenheim.speedify.data.idclasses;

import de.seniorenheim.speedify.data.entities.forwardingagencies.ForwardingAgency;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class RelationId implements Serializable {

    private ForwardingAgency forwardingAgency_1;
    private ForwardingAgency forwardingAgency_2;

    public RelationId(ForwardingAgency forwardingAgency_1, ForwardingAgency forwardingAgency_2) {
        this.forwardingAgency_1 = forwardingAgency_1;
        this.forwardingAgency_2 = forwardingAgency_2;
    }
}
