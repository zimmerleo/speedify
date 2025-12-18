package de.seniorenheim.speedify.data.idclasses;

import de.seniorenheim.speedify.data.entities.forwardingagencies.ranks.Permission;
import de.seniorenheim.speedify.data.entities.forwardingagencies.ranks.Rank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
public class Rank_Permission_Id implements Serializable {

    private Rank rank;
    private Permission permission;
}
