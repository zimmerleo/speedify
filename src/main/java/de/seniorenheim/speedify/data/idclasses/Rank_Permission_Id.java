package de.seniorenheim.speedify.data.idclasses;

import de.seniorenheim.speedify.data.entities.forwardingagencies.ranks.Permission;
import de.seniorenheim.speedify.data.entities.forwardingagencies.ranks.Rank;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class Rank_Permission_Id implements Serializable {

    private Rank rank;
    private Permission permission;

    public Rank_Permission_Id(Rank rank, Permission permission) {
        this.rank = rank;
        this.permission = permission;
    }
}
