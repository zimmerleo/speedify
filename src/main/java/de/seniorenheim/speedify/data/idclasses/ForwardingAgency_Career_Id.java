package de.seniorenheim.speedify.data.idclasses;

import de.seniorenheim.speedify.data.entities.forwardingagencies.careers.Career;
import de.seniorenheim.speedify.data.entities.forwardingagencies.ForwardingAgency;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Objects;

@EqualsAndHashCode
public class ForwardingAgency_Career_Id implements Serializable {

    private ForwardingAgency forwardingAgency;
    private Career career;

    public ForwardingAgency_Career_Id(ForwardingAgency forwardingAgency, Career career) {
        this.forwardingAgency = forwardingAgency;
        this.career = career;
    }
}
