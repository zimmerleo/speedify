package de.seniorenheim.speedify.idclasses;

import de.seniorenheim.speedify.entities.forwardingagencies.ranks.Permission;
import de.seniorenheim.speedify.entities.forwardingagencies.ranks.Rank;

import java.io.Serializable;
import java.util.Objects;

public class Rank_Permission_Id implements Serializable {

    private Rank rank;
    private Permission permission;

    public Rank_Permission_Id(Rank rank, Permission permission) {
        this.rank = rank;
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Rank_Permission_Id that = (Rank_Permission_Id) o;
        return Objects.equals(rank, that.rank) && Objects.equals(permission, that.permission);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(rank);
        result = 31 * result + Objects.hashCode(permission);
        return result;
    }
}
