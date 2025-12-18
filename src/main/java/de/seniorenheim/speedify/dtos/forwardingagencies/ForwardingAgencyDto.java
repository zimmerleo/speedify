package de.seniorenheim.speedify.dtos.forwardingagencies;

import de.seniorenheim.speedify.dtos.finance.BankAccountDto;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.forwardingagencies.ForwardingAgency}
 */
@Value
public class ForwardingAgencyDto implements Serializable {
    Long id;
    String name;
    String description;
    LevelDto level;
    Integer xp;
    LegalFormDto legalForm;
    List<BankAccountDto> bankAccounts;
}