package de.seniorenheim.speedify.data.idclasses;

import de.seniorenheim.speedify.data.entities.users.Specialization;
import de.seniorenheim.speedify.data.entities.users.User;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@Setter
public class UserSpecializationId implements Serializable {

    private User user;
    private Specialization specialization;
}
