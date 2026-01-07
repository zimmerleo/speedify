package de.seniorenheim.speedify.data.repositories.forwardingagencies;

import de.seniorenheim.speedify.data.entities.forwardingagencies.ForwardingAgency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForwardingAgencyRepository extends JpaRepository<ForwardingAgency, Long> {
    Optional<ForwardingAgency> findByBankAccount_Iban(String bankAccountIban);
}