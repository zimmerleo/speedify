package de.seniorenheim.speedify.data.entities.forwardingagencies.ranks;

import de.seniorenheim.speedify.data.idclasses.Rank_Permission_Id;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ranks_permissions")
@IdClass(Rank_Permission_Id.class)
public class Rank_Permission {
    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Rank rank;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Permission permission;
}