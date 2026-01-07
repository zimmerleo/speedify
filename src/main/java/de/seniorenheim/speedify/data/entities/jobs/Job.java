package de.seniorenheim.speedify.data.entities.jobs;

import de.seniorenheim.speedify.data.entities.locations.Company;
import de.seniorenheim.speedify.data.entities.trucks.Truck;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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

    LocalDateTime accepted;

    @ManyToOne
    @JoinColumn
    private Truck truck;

    @Column(scale = 2)
    private BigDecimal kilometersDriven;

    @Column(scale = 2)
    private BigDecimal hoursDriven;

    private LocalDateTime completed;

    private Integer xp;
}