package de.seniorenheim.speedify.repositories.forwardingagencies.careers;

import de.seniorenheim.speedify.entities.forwardingagencies.careers.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {
}