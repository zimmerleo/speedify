package de.seniorenheim.speedify.data.dtos.users;

import de.seniorenheim.speedify.data.entities.users.UserSpecialization;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link UserSpecialization}
 */
@Value
@Builder
@AllArgsConstructor
public class UserSpecializationResponseDto implements Serializable {
    UserResponseDto user;
    SpecializationResponseDto specialization;
    RankResponseDto rank;
    Integer xp;
}