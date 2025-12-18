package de.seniorenheim.speedify.dtos.jobs;

import de.seniorenheim.speedify.dtos.locations.CompanyDto;
import de.seniorenheim.speedify.dtos.trucks.TruckDto;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link de.seniorenheim.speedify.entities.jobs.Job}
 */
@Value
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