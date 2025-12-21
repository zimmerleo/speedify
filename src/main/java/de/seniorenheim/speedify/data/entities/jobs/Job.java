package de.seniorenheim.speedify.data.entities.jobs;

import de.seniorenheim.speedify.data.entities.locations.Company;
import de.seniorenheim.speedify.data.entities.trucks.Truck;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Builder.Default
    LocalDateTime accepted = null;

    @ManyToOne
    @JoinColumn
    @Builder.Default
    private Truck truck = null;

    @Builder.Default
    private Double kilometersDriven = 0D;

    @Builder.Default
    private Double hoursDriven = 0D;

    @Builder.Default
    private LocalDateTime completed = null;
}