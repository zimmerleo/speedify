package de.seniorenheim.speedify.idclasses;

import de.seniorenheim.speedify.entities.forwardingagencies.careers.Career;
import de.seniorenheim.speedify.entities.forwardingagencies.ForwardingAgency;

import java.io.Serializable;
import java.util.Objects;

public class ForwardingAgency_Career_Id implements Serializable {

    private ForwardingAgency forwardingAgency;
    private Career career;

    public ForwardingAgency_Career_Id(ForwardingAgency forwardingAgency, Career career) {
        this.forwardingAgency = forwardingAgency;
        this.career = career;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        ForwardingAgency_Career_Id that = (ForwardingAgency_Career_Id) o;
        return Objects.equals(forwardingAgency, that.forwardingAgency) && Objects.equals(career, that.career);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(forwardingAgency);
        result = 31 * result + Objects.hashCode(career);
        return result;
    }
}
