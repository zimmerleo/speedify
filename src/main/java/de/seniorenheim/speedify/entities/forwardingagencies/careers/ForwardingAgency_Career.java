package de.seniorenheim.speedify.entities.forwardingagencies.careers;

import de.seniorenheim.speedify.entities.forwardingagencies.ForwardingAgency;
import de.seniorenheim.speedify.idclasses.ForwardingAgency_Career_Id;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "forwarding_agencies_careers")
@IdClass(ForwardingAgency_Career_Id.class)
public class ForwardingAgency_Career {

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private ForwardingAgency forwardingAgency;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Career career;
}