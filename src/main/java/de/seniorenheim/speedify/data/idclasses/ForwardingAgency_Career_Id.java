package de.seniorenheim.speedify.data.idclasses;

import de.seniorenheim.speedify.data.entities.forwardingagencies.ForwardingAgency;
import de.seniorenheim.speedify.data.entities.forwardingagencies.careers.Career;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class ForwardingAgency_Career_Id implements Serializable {

    private ForwardingAgency forwardingAgency;
    private Career career;

    public ForwardingAgency_Career_Id(ForwardingAgency forwardingAgency, Career career) {
        this.forwardingAgency = forwardingAgency;
        this.career = career;
    }
}
