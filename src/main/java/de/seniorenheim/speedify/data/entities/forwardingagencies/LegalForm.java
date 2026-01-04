package de.seniorenheim.speedify.data.entities.forwardingagencies;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "legal_forms")
public class LegalForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(scale = 2, nullable = false)
    private BigDecimal capitalStock;

}