package de.seniorenheim.speedify.presentation;

import de.seniorenheim.speedify.business.services.TransactionService;
import de.seniorenheim.speedify.business.util.AuthenticationUtils;
import de.seniorenheim.speedify.business.util.EntityMapper;
import de.seniorenheim.speedify.data.dtos.finance.TransactionCreationDto;
import de.seniorenheim.speedify.data.dtos.finance.TransactionResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final EntityMapper entityMapper;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TransactionResponseDto> getAll() {
        return transactionService.getAll().stream().map(entityMapper::fromEntity).toList();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionResponseDto getById(@PathVariable Long id) {
        return entityMapper.fromEntity(transactionService.getById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody TransactionCreationDto transactionCreationDto) {
        transactionService.save(AuthenticationUtils.getCurrentUser().getUser().getBankAccount().getIban(), transactionCreationDto);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable Long id) {
        transactionService.delete(id);
    }


}
