package de.seniorenheim.speedify.data.repositories.finance;

import de.seniorenheim.speedify.data.entities.finance.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}