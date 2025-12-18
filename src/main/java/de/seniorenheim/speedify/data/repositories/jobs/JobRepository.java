package de.seniorenheim.speedify.data.repositories.jobs;

import de.seniorenheim.speedify.data.entities.jobs.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}