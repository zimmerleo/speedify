package de.seniorenheim.speedify.idclasses;

import de.seniorenheim.speedify.entities.dlcs.DLC;
import de.seniorenheim.speedify.entities.users.User;

import java.io.Serializable;
import java.util.Objects;

public class DLCOwnershipId implements Serializable {

    private User user;
    private DLC dlc;

    public DLCOwnershipId(User user, DLC dlc) {
        this.user = user;
        this.dlc = dlc;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        DLCOwnershipId that = (DLCOwnershipId) o;
        return Objects.equals(user, that.user) && Objects.equals(dlc, that.dlc);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(user);
        result = 31 * result + Objects.hashCode(dlc);
        return result;
    }
}
