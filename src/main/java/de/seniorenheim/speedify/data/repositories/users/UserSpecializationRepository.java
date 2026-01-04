package de.seniorenheim.speedify.data.repositories.users;

import de.seniorenheim.speedify.data.entities.users.UserSpecialization;
import de.seniorenheim.speedify.data.idclasses.UserSpecializationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSpecializationRepository extends JpaRepository<UserSpecialization, UserSpecializationId> {
    List<UserSpecialization> findAllByUser_Id(Long userId);

    List<UserSpecialization> findAllBySpecialization_Id(Long specializationId);

    UserSpecialization findByUser_IdAndSpecialization_Id(Long userId, Long specializationId);

}