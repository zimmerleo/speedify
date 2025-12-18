package de.seniorenheim.speedify.data.dtos.jobs;

import de.seniorenheim.speedify.data.dtos.locations.CompanyDto;
import de.seniorenheim.speedify.data.dtos.trucks.TruckDto;
import de.seniorenheim.speedify.data.entities.jobs.Job;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Job}
 */
@Value
@Builder
@AllArgsConstructor
public class JobDto implements Serializable {
    Long id;
    PayloadDto payload;
    CompanyDto origin;
    CompanyDto destination;
    LocalDateTime accepted;
    TruckDto truck;
    Double kilometersDriven;
    Double hoursDriven;
    LocalDateTime completed;
}