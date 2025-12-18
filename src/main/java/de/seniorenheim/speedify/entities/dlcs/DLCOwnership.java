package de.seniorenheim.speedify.entities.dlcs;

import de.seniorenheim.speedify.entities.users.User;
import de.seniorenheim.speedify.idclasses.DLCOwnershipId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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