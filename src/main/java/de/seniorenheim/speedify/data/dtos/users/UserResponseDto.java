package de.seniorenheim.speedify.data.dtos.users;

import de.seniorenheim.speedify.data.dtos.dlcs.DLCResponseDto;
import de.seniorenheim.speedify.data.dtos.finance.BankAccountResponseDto;
import de.seniorenheim.speedify.data.entities.users.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link User}
 */
@Value
@Builder
@AllArgsConstructor
public class UserResponseDto implements Serializable {
    Long id;
    String name;
    String email;
    String password;
    BankAccountResponseDto bankAccount;
    Boolean administrator;
    List<DLCResponseDto> dlcs;
    LocalDateTime creationDate;
}