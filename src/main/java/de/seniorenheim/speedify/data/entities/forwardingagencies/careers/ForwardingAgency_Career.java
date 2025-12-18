package de.seniorenheim.speedify.data.entities.forwardingagencies.careers;

import de.seniorenheim.speedify.data.entities.forwardingagencies.ForwardingAgency;
import de.seniorenheim.speedify.data.idclasses.ForwardingAgency_Career_Id;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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