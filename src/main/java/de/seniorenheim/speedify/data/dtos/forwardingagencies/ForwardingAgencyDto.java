package de.seniorenheim.speedify.data.dtos.forwardingagencies;

import de.seniorenheim.speedify.data.dtos.finance.BankAccountDto;
import de.seniorenheim.speedify.data.entities.forwardingagencies.ForwardingAgency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link ForwardingAgency}
 */
@Value
@Builder
@AllArgsConstructor
public class ForwardingAgencyDto implements Serializable {
    Long id;
    String name;
    String description;
    LevelDto level;
    Integer xp;
    LegalFormDto legalForm;
    List<BankAccountDto> bankAccounts;
}