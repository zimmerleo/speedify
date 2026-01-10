package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.entities.finance.BankAccount;
import de.seniorenheim.speedify.data.repositories.finance.BankAccountRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@AllArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final Random random = new Random();

    public List<BankAccount> getAll() {
        return bankAccountRepository.findAll();
    }

    public BankAccount getByIban(String iban) {
        return bankAccountRepository.findByIban(iban).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public BankAccount createUserAccount() {
        BankAccount bankAccount = BankAccount.builder()
                .iban(randomizeIban())
                .balance(BigDecimal.valueOf(5000))
                .build();
        return bankAccountRepository.save(bankAccount);
    }

    @Transactional
    public BankAccount createForwardingAgencyAccount(BigDecimal capitalStock) {
        BankAccount bankAccount = BankAccount.builder()
                .iban(randomizeIban())
                .balance(capitalStock)
                .build();
        return bankAccountRepository.save(bankAccount);
    }

    @Transactional
    public void update(String iban, BigDecimal amount) {
        BankAccount entity = getByIban(iban);
        entity.setBalance(entity.getBalance().add(amount));
        bankAccountRepository.save(entity);
    }

    private String randomizeIban() {
        String iban;
        do {
            iban = "ET52";
            for (int i = 1; i < 19; i++) {
                iban = iban.concat(String.valueOf(random.nextInt(10)));
            }
        } while (bankAccountRepository.findByIban(iban).isPresent());
        return iban;
    }
}
