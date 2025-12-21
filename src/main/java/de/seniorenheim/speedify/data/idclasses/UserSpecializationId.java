package de.seniorenheim.speedify.data.idclasses;

import de.seniorenheim.speedify.data.entities.users.Specialization;
import de.seniorenheim.speedify.data.entities.users.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
public class UserSpecializationId implements Serializable {

    private User user;
    private Specialization specialization;
}
