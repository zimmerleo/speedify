package de.seniorenheim.speedify.data.entities.trucks;

import de.seniorenheim.speedify.data.entities.users.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trucks")
public class Truck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String licensePlate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TruckType type;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User owner;
}