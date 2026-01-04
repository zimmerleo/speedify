package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.business.util.AuthenticationUtils;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships.ApplicationCreationDto;
import de.seniorenheim.speedify.data.entities.forwardingagencies.memberships.Application;
import de.seniorenheim.speedify.data.repositories.forwardingagencies.ForwardingAgencyRepository;
import de.seniorenheim.speedify.data.repositories.forwardingagencies.memberships.ApplicationRepository;
import de.seniorenheim.speedify.data.repositories.forwardingagencies.memberships.roles.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ForwardingAgencyRepository forwardingAgencyRepository;
    private final RoleRepository roleRepository;

    public List<Application> getAll() {
        return applicationRepository.findAll();
    }

    public List<Application> getAllByUserId(Long userId) {
        return applicationRepository.findAllByUser_Id(userId);
    }

    public List<Application> getAllByForwardingAgencyId(Long forwardingAgencyId) {
        return applicationRepository.findAllByForwardingAgency_Id(forwardingAgencyId);
    }

    public Application getById(Long id) {
        return applicationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void save(Long forwardingAgencyId, ApplicationCreationDto applicationCreationDto) {
        Application entity = Application.builder()
                .user(AuthenticationUtils.getCurrentUser().getUser())
                .forwardingAgency(forwardingAgencyRepository.getReferenceById(forwardingAgencyId))
                .role(roleRepository.getReferenceById(applicationCreationDto.getRole()))
                .text(applicationCreationDto.getText())
                .build();
        applicationRepository.save(entity);
    }

    @Transactional
    public void delete(long id) {
        applicationRepository.deleteById(id);
    }
}
