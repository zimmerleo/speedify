package de.seniorenheim.speedify.data.repositories.users;

import de.seniorenheim.speedify.data.entities.users.User_Specialization;
import de.seniorenheim.speedify.data.idclasses.UserSpecializationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_SpecializationRepository extends JpaRepository<User_Specialization, UserSpecializationId> {
}