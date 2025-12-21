package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.entities.finance.BankAccount;
import de.seniorenheim.speedify.data.repositories.finance.BankAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Slf4j
@AllArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public List<BankAccount> getAll() {
        return bankAccountRepository.findAll();
    }

    public BankAccount getByIban(String iban) {
        return bankAccountRepository.findByIban(iban).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public BankAccount createUserAccount() {
        BankAccount.BankAccountBuilder bankAccount = BankAccount.builder();
        String iban;

        do {
            iban = randomizeIban();
        } while (bankAccountRepository.findByIban(iban).isPresent());

        bankAccount.iban(iban);
        return bankAccountRepository.save(bankAccount.build());
    }

    @Transactional
    public BankAccount createForwardingAgencyAccount(Double capitalStock) {
        BankAccount.BankAccountBuilder bankAccount = BankAccount.builder();
        String iban;

        do {
            iban = randomizeIban();
        } while (bankAccountRepository.findByIban(iban).isPresent());

        bankAccount.iban(iban);
        bankAccount.balance(capitalStock);
        return bankAccountRepository.save(bankAccount.build());
    }

    @Transactional
    public void update(String iban, Double amount) {
        BankAccount entity = getByIban(iban);
        entity.setBalance(entity.getBalance() + amount);
        bankAccountRepository.save(entity);
    }

    @Transactional
    public void delete(Long id) {
        bankAccountRepository.deleteById(id);
    }

    private String randomizeIban() {
        Random random = new Random();
        String iban = "ET52";
        for (int i = 0; i < 19; i++) {
            iban.concat(String.valueOf(random.nextInt(10)));
        }
        return iban;
    }
}
