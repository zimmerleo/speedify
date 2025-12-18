package de.seniorenheim.speedify.data.entities.locations;

import de.seniorenheim.speedify.data.entities.dlcs.DLC;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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