package de.seniorenheim.speedify.data.idclasses;

import de.seniorenheim.speedify.data.entities.dlcs.DLC;
import de.seniorenheim.speedify.data.entities.users.User;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Objects;

@EqualsAndHashCode
public class DLCOwnershipId implements Serializable {

    private User user;
    private DLC dlc;

    public DLCOwnershipId(User user, DLC dlc) {
        this.user = user;
        this.dlc = dlc;
    }
}
