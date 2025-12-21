package de.seniorenheim.speedify.data.dtos.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link de.seniorenheim.speedify.data.entities.users.User_Specialization}
 */
@Value
@Builder
@AllArgsConstructor
public class User_SpecializationResponseDto implements Serializable {
    UserResponseDto user;
    SpecializationResponseDto specialization;
    RankResponseDto rank;
    Integer xp;
}