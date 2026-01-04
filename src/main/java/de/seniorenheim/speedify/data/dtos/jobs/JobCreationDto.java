package de.seniorenheim.speedify.data.dtos.jobs;

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
public class JobCreationDto implements Serializable {
    Long payload;
    Long origin;
    Long destination;
    LocalDateTime accepted;
    Long truck;
    BigDecimal kilometersDriven;
    BigDecimal hoursDriven;
    LocalDateTime completed;
    Integer xp;
}