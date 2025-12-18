package de.seniorenheim.speedify.idclasses;

import de.seniorenheim.speedify.entities.forwardingagencies.ForwardingAgency;
import de.seniorenheim.speedify.entities.forwardingagencies.ranks.Rank;

import java.io.Serializable;
import java.util.Objects;

public class ForwardingAgency_Rank_Id implements Serializable {

    private ForwardingAgency forwardingAgency;
    private Rank rank;

    public ForwardingAgency_Rank_Id(ForwardingAgency forwardingAgency, Rank rank) {
        this.forwardingAgency = forwardingAgency;
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        ForwardingAgency_Rank_Id that = (ForwardingAgency_Rank_Id) o;
        return Objects.equals(forwardingAgency, that.forwardingAgency) && Objects.equals(rank, that.rank);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(forwardingAgency);
        result = 31 * result + Objects.hashCode(rank);
        return result;
    }
}
