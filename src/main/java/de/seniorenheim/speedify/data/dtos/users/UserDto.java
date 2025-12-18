package de.seniorenheim.speedify.data.dtos.users;

import de.seniorenheim.speedify.data.dtos.finance.BankAccountDto;
import de.seniorenheim.speedify.data.entities.users.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Value
@Builder
@AllArgsConstructor
public class UserDto implements Serializable {
    Long id;
    String name;
    String email;
    String password;
    BankAccountDto bankAccount;
    Boolean administrator;
}