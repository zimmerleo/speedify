package de.seniorenheim.speedify.data.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.data.entities.users.Specialization}
 */
@Value
@Builder
@AllArgsConstructor
public class SpecializationResponseDto implements Serializable {
    Long id;
    String name;
}