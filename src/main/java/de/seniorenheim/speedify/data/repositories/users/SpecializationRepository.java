package de.seniorenheim.speedify.data.repositories.users;

import de.seniorenheim.speedify.data.entities.users.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
}