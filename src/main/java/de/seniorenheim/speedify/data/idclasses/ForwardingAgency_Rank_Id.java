package de.seniorenheim.speedify.data.idclasses;

import de.seniorenheim.speedify.data.entities.forwardingagencies.ForwardingAgency;
import de.seniorenheim.speedify.data.entities.forwardingagencies.ranks.Rank;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class ForwardingAgency_Rank_Id implements Serializable {

    private ForwardingAgency forwardingAgency;
    private Rank rank;

    public ForwardingAgency_Rank_Id(ForwardingAgency forwardingAgency, Rank rank) {
        this.forwardingAgency = forwardingAgency;
        this.rank = rank;
    }
}
