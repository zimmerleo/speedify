package de.seniorenheim.speedify.data.repositories.finance;

import de.seniorenheim.speedify.data.entities.finance.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}