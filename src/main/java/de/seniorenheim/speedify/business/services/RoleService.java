package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.entities.forwardingagencies.memberships.roles.Role;
import de.seniorenheim.speedify.data.repositories.forwardingagencies.memberships.roles.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role getById(Long id) {
        return roleRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Role getLeader() {
        return roleRepository.findBySuperiorEquals(null);
    }
}
