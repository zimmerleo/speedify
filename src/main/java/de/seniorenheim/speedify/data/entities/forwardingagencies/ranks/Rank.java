package de.seniorenheim.speedify.data.entities.forwardingagencies.ranks;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ranks")
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn
    private Rank superior;

    @ManyToOne
    @JoinColumn
    private Rank subordinate;

}