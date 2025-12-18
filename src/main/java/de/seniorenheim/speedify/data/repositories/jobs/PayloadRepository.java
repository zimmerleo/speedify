package de.seniorenheim.speedify.data.repositories.jobs;

import de.seniorenheim.speedify.data.entities.jobs.Payload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayloadRepository extends JpaRepository<Payload, Long> {
}