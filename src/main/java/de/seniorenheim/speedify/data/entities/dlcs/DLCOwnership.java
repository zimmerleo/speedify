package de.seniorenheim.speedify.data.entities.dlcs;

import de.seniorenheim.speedify.data.entities.users.User;
import de.seniorenheim.speedify.data.idclasses.DLCOwnershipId;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dlc_ownerships")
@IdClass(DLCOwnershipId.class)
public class DLCOwnership {

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private DLC dlc;
}