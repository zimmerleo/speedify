package de.seniorenheim.speedify.data.dtos.dlcs;

import de.seniorenheim.speedify.data.entities.dlcs.DLCOwnership;
import de.seniorenheim.speedify.data.dtos.users.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link DLCOwnership}
 */
@Value
@Builder
@AllArgsConstructor
public class DLCOwnershipDto implements Serializable {
    UserDto user;
    DLCDto dlc;
}