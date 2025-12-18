package de.seniorenheim.speedify.repositories.forwardingagencies;

import de.seniorenheim.speedify.entities.forwardingagencies.ForwardingAgency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForwardingAgencyRepository extends JpaRepository<ForwardingAgency, Long> {
}