package de.seniorenheim.speedify.dtos.users;

import de.seniorenheim.speedify.dtos.finance.BankAccountDto;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.users.User}
 */
@Value
public class UserDto implements Serializable {
    Long id;
    String name;
    String email;
    String password;
    BankAccountDto bankAccount;
    Boolean administrator;
}