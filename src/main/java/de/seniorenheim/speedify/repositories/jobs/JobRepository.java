package de.seniorenheim.speedify.repositories.jobs;

import de.seniorenheim.speedify.entities.jobs.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}