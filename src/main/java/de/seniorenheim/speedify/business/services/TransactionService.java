package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.business.util.AuthenticationUtils;
import de.seniorenheim.speedify.data.dtos.finance.TransactionCreationDto;
import de.seniorenheim.speedify.data.entities.finance.BankAccount;
import de.seniorenheim.speedify.data.entities.finance.Transaction;
import de.seniorenheim.speedify.data.entities.jobs.Job;
import de.seniorenheim.speedify.data.entities.users.LoginUser;
import de.seniorenheim.speedify.data.repositories.finance.TransactionPurposeRepository;
import de.seniorenheim.speedify.data.repositories.finance.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final BankAccountService bankAccountService;
    private final JobService jobService;
    private final TransactionPurposeRepository transactionPurposeRepository;

    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getAllByCurrentUser() {
        LoginUser loginUser = AuthenticationUtils.getCurrentUser();
        String iban = loginUser.getUser().getBankAccount().getIban();

        return transactionRepository.findAllByPayerIbanOrPayee_Iban(iban, iban);
    }

    public Transaction getById(Long id) {
        return transactionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void save(String payerIban, TransactionCreationDto transactionCreationDto) {
        BankAccount payer = bankAccountService.getByIban(payerIban);
        BankAccount payee = bankAccountService.getByIban(transactionCreationDto.getPayeeIban());

        if (payer.getBalance().compareTo(transactionCreationDto.getAmount()) < 0) return;

        Transaction.TransactionBuilder transactionBuilder = Transaction.builder()
                .payer(payer)
                .payee(payee)
                .amount(transactionCreationDto.getAmount())
                .purpose(transactionPurposeRepository.getReferenceById(transactionCreationDto.getPurpose()))
                .job(jobService.getById(transactionCreationDto.getJob()));

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
