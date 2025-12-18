package de.seniorenheim.speedify.repositories.jobs;

import de.seniorenheim.speedify.entities.jobs.Payload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayloadRepository extends JpaRepository<Payload, Long> {
}