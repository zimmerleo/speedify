package de.seniorenheim.speedify.data.entities.users;

import de.seniorenheim.speedify.data.idclasses.UserSpecializationId;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users_specializations")
@IdClass(UserSpecializationId.class)
public class UserSpecialization {

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    private Specialization specialization;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Rank rank;

    @Builder.Default
    private Integer xp = 0;
}