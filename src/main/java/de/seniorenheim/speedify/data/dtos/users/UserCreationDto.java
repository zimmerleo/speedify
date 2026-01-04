package de.seniorenheim.speedify.data.dtos.users;

import de.seniorenheim.speedify.data.entities.users.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link User}
 */
@Value
@Builder
@AllArgsConstructor
public class UserCreationDto implements Serializable {
    String name;
    String email;
    String password;
    List<Long> dlcs;
}