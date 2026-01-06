package de.seniorenheim.speedify.data.repositories.finance;

import de.seniorenheim.speedify.data.entities.finance.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByPayerIbanOrPayee_Iban(String payerIban, String payeeIban);
}