package de.seniorenheim.speedify.data.idclasses;

import de.seniorenheim.speedify.data.entities.forwardingagencies.ForwardingAgency;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
public class RelationId implements Serializable {

    private ForwardingAgency forwardingAgency_1;
    private ForwardingAgency forwardingAgency_2;
}
