package de.seniorenheim.speedify.data.repositories.forwardingagencies;

import de.seniorenheim.speedify.data.entities.forwardingagencies.LegalForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegalFormRepository extends JpaRepository<LegalForm, Long> {
}