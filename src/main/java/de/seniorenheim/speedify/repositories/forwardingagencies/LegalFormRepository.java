package de.seniorenheim.speedify.repositories.forwardingagencies;

import de.seniorenheim.speedify.entities.forwardingagencies.LegalForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegalFormRepository extends JpaRepository<LegalForm, Long> {
}