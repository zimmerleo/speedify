package de.seniorenheim.speedify.data.dtos.forwardingagencies;

import de.seniorenheim.speedify.data.dtos.finance.BankAccountResponseDto;
import de.seniorenheim.speedify.data.entities.forwardingagencies.ForwardingAgency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ForwardingAgency}
 */
@Value
@Builder
@AllArgsConstructor
public class ForwardingAgencyResponseDto implements Serializable {
    Long id;
    String name;
    String code;
    String description;
    LevelResponseDto level;
    Integer xp;
    LegalFormResponseDto legalForm;
    BankAccountResponseDto bankAccount;
}