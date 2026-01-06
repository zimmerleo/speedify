package de.seniorenheim.speedify.data.repositories.finance;

import de.seniorenheim.speedify.data.entities.finance.TransactionPurpose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionPurposeRepository extends JpaRepository<TransactionPurpose, Long> {
}