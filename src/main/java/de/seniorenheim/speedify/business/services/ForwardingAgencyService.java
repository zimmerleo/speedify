package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.business.util.AuthenticationUtils;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.ForwardingAgencyCreationDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships.MembershipCreationDto;
import de.seniorenheim.speedify.data.entities.forwardingagencies.ForwardingAgency;
import de.seniorenheim.speedify.data.entities.forwardingagencies.LegalForm;
import de.seniorenheim.speedify.data.entities.jobs.Job;
import de.seniorenheim.speedify.data.repositories.forwardingagencies.ForwardingAgencyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ForwardingAgencyService {

    private final ForwardingAgencyRepository forwardingAgencyRepository;
    private final LevelService levelService;
    private final LegalFormService legalFormService;
    private final BankAccountService bankAccountService;
    private final MembershipService membershipService;
    private final ApplicationService applicationService;
    private final RoleService roleService;

    public List<ForwardingAgency> getAll() {
        return forwardingAgencyRepository.findAll();
    }

    public ForwardingAgency getById(Long id) {
        return forwardingAgencyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public ForwardingAgency getByIban(String iban) {
        return forwardingAgencyRepository.findByBankAccount_Iban(iban).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void save(ForwardingAgencyCreationDto forwardingAgencyCreationDto) {
        LegalForm legalForm = legalFormService.getById(forwardingAgencyCreationDto.getLegalForm());
        ForwardingAgency entity = ForwardingAgency.builder()
                .name(forwardingAgencyCreationDto.getName())
                .code(forwardingAgencyCreationDto.getCode())
                .description(forwardingAgencyCreationDto.getDescription())
                .level(levelService.getFirst())
                .legalForm(legalForm)
                .bankAccount(bankAccountService.createForwardingAgencyAccount(legalForm.getCapitalStock()))
                .build();
        entity = forwardingAgencyRepository.save(entity);
        membershipService.save(entity.getId(), MembershipCreationDto.builder()
                .user(AuthenticationUtils.getCurrentUser().getUser().getId())
                .role(roleService.getLeader().getId())
                .build());
    }

    @Transactional
    public void update(long id, ForwardingAgencyCreationDto forwardingAgencyCreationDto) {
        ForwardingAgency entity = getById(id);
        if (forwardingAgencyCreationDto.getName() != null) {
            entity.setName(forwardingAgencyCreationDto.getName());
        }
        if (forwardingAgencyCreationDto.getCode() != null) {
            entity.setCode(forwardingAgencyCreationDto.getCode());
        }
        if (forwardingAgencyCreationDto.getDescription() != null) {
            entity.setCode(forwardingAgencyCreationDto.getDescription());
        }
        if (forwardingAgencyCreationDto.getLegalForm() != null) {
            entity.setLegalForm(legalFormService.getById(forwardingAgencyCreationDto.getLegalForm()));
        }
        forwardingAgencyRepository.save(entity);
    }

    @Transactional
    public void applyXP(Job job) {
        ForwardingAgency forwardingAgency = membershipService.getCurrentMembership(job.getTruck().getOwner().getId()).getForwardingAgency();

        int currentXp = forwardingAgency.getXp();
        int xpNeeded = forwardingAgency.getLevel().getSuperior() != null ? forwardingAgency.getLevel().getSuperior().getXpNeeded() : Integer.MAX_VALUE;

        if (currentXp + job.getXp() >= xpNeeded) {
            forwardingAgency.setLevel(forwardingAgency.getLevel().getSuperior());
            forwardingAgency.setXp(currentXp + job.getXp() - xpNeeded);
        } else {
            forwardingAgency.setXp(currentXp + job.getXp());
        }
        forwardingAgencyRepository.save(forwardingAgency);
    }

    @Transactional
    public void delete(long id) {
        membershipService.getAllByForwardingAgencyId(id).forEach(membership -> membershipService.delete(membership.getId()));
        applicationService.getAllByForwardingAgencyId(id).forEach(application -> applicationService.deleteByForwardingAgency(id, application.getId()));
        forwardingAgencyRepository.deleteById(id);
    }
}
