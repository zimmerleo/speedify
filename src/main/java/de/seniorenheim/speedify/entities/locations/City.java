package de.seniorenheim.speedify.entities.locations;

import de.seniorenheim.speedify.entities.dlcs.DLC;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Country locatedIn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = true)
    private DLC dlc;
}