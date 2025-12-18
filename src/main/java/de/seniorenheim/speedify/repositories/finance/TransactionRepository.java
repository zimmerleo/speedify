package de.seniorenheim.speedify.repositories.finance;

import de.seniorenheim.speedify.entities.finance.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}