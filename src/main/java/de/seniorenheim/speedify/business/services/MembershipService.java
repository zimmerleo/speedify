package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.business.util.AuthenticationUtils;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships.MembershipCreationDto;
import de.seniorenheim.speedify.data.entities.forwardingagencies.memberships.Application;
import de.seniorenheim.speedify.data.entities.forwardingagencies.memberships.Membership;
import de.seniorenheim.speedify.data.entities.forwardingagencies.memberships.roles.Role;
import de.seniorenheim.speedify.data.repositories.forwardingagencies.ForwardingAgencyRepository;
import de.seniorenheim.speedify.data.repositories.forwardingagencies.memberships.MembershipRepository;
import de.seniorenheim.speedify.data.repositories.forwardingagencies.memberships.roles.RoleRepository;
import de.seniorenheim.speedify.data.repositories.users.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;
    private final UserRepository userRepository;
    private final ForwardingAgencyRepository forwardingAgencyRepository;
    private final RoleRepository roleRepository;
    private final ApplicationService applicationService;

    public List<Membership> getAll() {
        return membershipRepository.findAll();
    }

    public List<Membership> getAllByUserId(Long userId) {
        return membershipRepository.findAllByUser_Id(userId);
    }

    public List<Membership> getAllByForwardingAgencyId(Long forwardingAgencyId) {
        return membershipRepository.findAllByForwardingAgency_Id(forwardingAgencyId);
    }

    public Membership getById(Long id) {
        return membershipRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Membership getCurrentMembership(Long userId) {
        return membershipRepository.findByUser_IdAndUntil(userId, LocalDate.of(9999, 12, 31)).orElse(null);
    }

    public Membership getByUserAndTimestampBetweenSinceAndUntil(Long userId, LocalDate timestamp) {
        return membershipRepository.findByUser_IdAndSinceBeforeOrSinceEqualsAndUntilAfter(userId, timestamp, timestamp, timestamp).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void save(Long forwardingAgencyId, MembershipCreationDto membershipCreationDto) {
        Membership currentMembership = getCurrentMembership(membershipCreationDto.getUser());
        if (currentMembership != null) {
            end(currentMembership.getId());
        }
        for (Application application : applicationService.getAllByUserId(membershipCreationDto.getUser())) {
            applicationService.delete(application.getId());
        }
        Membership entity = Membership.builder()
                .user(userRepository.getReferenceById(membershipCreationDto.getUser()))
                .forwardingAgency(forwardingAgencyRepository.getReferenceById(forwardingAgencyId))
                .role(roleRepository.getReferenceById(membershipCreationDto.getRole()))
                .since(LocalDate.now())
                .until(LocalDate.of(9999, 12, 31))
                .build();
        membershipRepository.save(entity);
    }

    @Transactional
    public void end(Long id) {
        Membership entity = getById(id);
        Role superior = entity.getRole().getSuperior();

        while (!superior.getId().equals(AuthenticationUtils.getCurrentUser().getRole().getId())) {
            if (superior.getSuperior() == null) return;
            superior = superior.getSuperior();
        }
        entity.setUntil(LocalDate.now());
        membershipRepository.save(entity);
    }

    @Transactional
    public void delete(Long id) {
        membershipRepository.deleteById(id);
    }
}
