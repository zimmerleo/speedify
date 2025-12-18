package de.seniorenheim.speedify.repositories.finance;

import de.seniorenheim.speedify.entities.finance.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}