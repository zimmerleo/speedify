package de.seniorenheim.speedify.data.entities.locations;

import de.seniorenheim.speedify.data.entities.dlcs.DLC;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
    private Country locatedIn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private DLC dlc;

    @ManyToMany
    @JoinTable(
            name = "cities_companies",
            joinColumns = @JoinColumn(name = "city_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id")
    )
    private List<Company> companies;
}