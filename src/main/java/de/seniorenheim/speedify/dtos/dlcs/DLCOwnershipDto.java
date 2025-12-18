package de.seniorenheim.speedify.dtos.dlcs;

import de.seniorenheim.speedify.dtos.users.UserDto;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.dlcs.DLCOwnership}
 */
@Value
public class DLCOwnershipDto implements Serializable {
    UserDto user;
    DLCDto dlc;
}