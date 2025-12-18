package de.seniorenheim.speedify.idclasses;

import de.seniorenheim.speedify.entities.forwardingagencies.ForwardingAgency;

import java.io.Serializable;
import java.util.Objects;

public class RelationId implements Serializable {

    private ForwardingAgency forwardingAgency_1;
    private ForwardingAgency forwardingAgency_2;

    public RelationId(ForwardingAgency forwardingAgency_1, ForwardingAgency forwardingAgency_2) {
        this.forwardingAgency_1 = forwardingAgency_1;
        this.forwardingAgency_2 = forwardingAgency_2;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        RelationId that = (RelationId) o;
        return Objects.equals(forwardingAgency_1, that.forwardingAgency_1) && Objects.equals(forwardingAgency_2, that.forwardingAgency_2);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(forwardingAgency_1);
        result = 31 * result + Objects.hashCode(forwardingAgency_2);
        return result;
    }
}
