package de.seniorenheim.speedify.entities.jobs;

import de.seniorenheim.speedify.entities.locations.Company;
import de.seniorenheim.speedify.entities.trucks.Truck;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Payload payload;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Company origin;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Company destination;

    private LocalDateTime accepted;

    @ManyToOne
    @JoinColumn
    private Truck truck;

    private Double kilometersDriven;

    private Double hoursDriven;

    private LocalDateTime completed;
}