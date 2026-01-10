package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.business.util.FinanceValues;
import de.seniorenheim.speedify.data.dtos.finance.TransactionCreationDto;
import de.seniorenheim.speedify.data.entities.finance.BankAccount;
import de.seniorenheim.speedify.data.entities.finance.Transaction;
import de.seniorenheim.speedify.data.repositories.finance.TransactionPurposeRepository;
import de.seniorenheim.speedify.data.repositories.finance.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private final UserService userService;

    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getAllByProcessedAtAfter(LocalDateTime timestamp) {
        return transactionRepository.findAllByProcessedAtAfter(timestamp);
    }

    public List<Transaction> getAllByUserId(Long userId) {
        String iban = userService.getById(userId).getBankAccount().getIban();
        return transactionRepository.findAllByPayerIbanOrPayee_Iban(iban, iban);
    }

    public Transaction getById(Long id) {
        return transactionRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void save(String payerIban, TransactionCreationDto transactionCreationDto) {
        BankAccount payer = payerIban != null ? bankAccountService.getByIban(payerIban) : null;
        BankAccount payee = transactionCreationDto.getPayeeIban() != null ? bankAccountService.getByIban(transactionCreationDto.getPayeeIban()) : null;

        if (payer != null && payer.getBalance().compareTo(transactionCreationDto.getAmount()) < 0) return;

        Transaction.TransactionBuilder transactionBuilder = Transaction.builder()
                .payer(payer)
                .payee(payee)
                .amount(transactionCreationDto.getAmount())
                .purpose(transactionPurposeRepository.getReferenceById(transactionCreationDto.getPurpose()))
                .job(transactionCreationDto.getJob() != null ? jobService.getById(transactionCreationDto.getJob()) : null);

        if (payer != null) bankAccountService.update(payer.getIban(), transactionCreationDto.getAmount().negate());
        if (payee != null) bankAccountService.update(payee.getIban(), transactionCreationDto.getAmount());

        transactionBuilder.processedAt(LocalDateTime.now());
        Transaction transaction = transactionRepository.save(transactionBuilder.build());

        if (transactionCreationDto.getPurpose().equals(3L)) {
            Transaction ustTransaction = Transaction.builder()
                    .payer(payee)
                    .payee(payer)
                    .amount(transactionCreationDto.getAmount().divide(BigDecimal.ONE.subtract(FinanceValues.umsatzsteuersatz), RoundingMode.HALF_EVEN).subtract(transactionCreationDto.getAmount()))
                    .purpose(transactionPurposeRepository.getReferenceById(15L))
                    .job(transactionCreationDto.getJob() != null ? jobService.getById(transactionCreationDto.getJob()) : null)
                    .processedAt(transaction.getProcessedAt())
                    .build();
            transactionRepository.save(ustTransaction);
        }
    }

    @Transactional
    public void delete(Long id) {
        Transaction transaction = getById(id);

        bankAccountService.update(transaction.getPayer().getIban(), transaction.getAmount());
        bankAccountService.update(transaction.getPayee().getIban(), transaction.getAmount().negate());

        transactionRepository.deleteById(id);
    }
}
