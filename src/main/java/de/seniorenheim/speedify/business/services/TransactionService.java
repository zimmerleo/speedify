package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.dtos.finance.TransactionCreationDto;
import de.seniorenheim.speedify.data.entities.finance.BankAccount;
import de.seniorenheim.speedify.data.entities.finance.Transaction;
import de.seniorenheim.speedify.data.repositories.finance.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final BankAccountService bankAccountService;

    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    public Transaction getById(Long id) {
        return transactionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void save(TransactionCreationDto transactionCreationDto) {
        BankAccount payer = bankAccountService.getByIban(transactionCreationDto.getPayerIban());
        BankAccount payee = bankAccountService.getByIban(transactionCreationDto.getPayeeIban());

        if (payer.getBalance().compareTo(transactionCreationDto.getAmount()) < 0) return;

        Transaction.TransactionBuilder transactionBuilder = Transaction.builder()
                .payer(payer)
                .payee(payee)
                .amount(transactionCreationDto.getAmount())
                .purpose(transactionCreationDto.getPurpose());

        bankAccountService.update(payer.getIban(), transactionCreationDto.getAmount().negate());
        bankAccountService.update(payee.getIban(), transactionCreationDto.getAmount());

        transactionBuilder.processedAt(LocalDateTime.now());
        transactionRepository.save(transactionBuilder.build());
    }

    @Transactional
    public void delete(Long id) {
        Transaction transaction = getById(id);

        bankAccountService.update(transaction.getPayer().getIban(), transaction.getAmount());
        bankAccountService.update(transaction.getPayee().getIban(), transaction.getAmount().negate());

        transactionRepository.deleteById(id);
    }
}
