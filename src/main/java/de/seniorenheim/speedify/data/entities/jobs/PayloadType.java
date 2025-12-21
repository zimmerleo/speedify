package de.seniorenheim.speedify.data.entities.jobs;

import de.seniorenheim.speedify.data.entities.users.Specialization;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payload_types")
public class PayloadType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn
    private Specialization specialization;

}