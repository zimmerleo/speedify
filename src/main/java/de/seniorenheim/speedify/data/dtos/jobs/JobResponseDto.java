package de.seniorenheim.speedify.data.dtos.jobs;

import de.seniorenheim.speedify.data.dtos.locations.CompanyResponseDto;
import de.seniorenheim.speedify.data.dtos.trucks.TruckResponseDto;
import de.seniorenheim.speedify.data.entities.jobs.Job;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link Job}
 */
@Value
@Builder
@AllArgsConstructor
public class JobResponseDto implements Serializable {
    Long id;
    PayloadResponseDto payload;
    CompanyResponseDto origin;
    CompanyResponseDto destination;
    LocalDateTime accepted;
    TruckResponseDto truck;
    BigDecimal kilometersDriven;
    BigDecimal hoursDriven;
    LocalDateTime completed;
    Integer xp;
}