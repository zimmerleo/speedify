package de.seniorenheim.speedify.data.dtos.jobs;

import de.seniorenheim.speedify.data.dtos.users.SpecializationResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.data.entities.jobs.PayloadType}
 */
@Value
@Builder
@AllArgsConstructor
public class PayloadTypeResponseDto implements Serializable {
    Long id;
    String name;
    SpecializationResponseDto specialization;
}