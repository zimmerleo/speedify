package de.seniorenheim.speedify.data.repositories.forwardingagencies.memberships.roles;

import de.seniorenheim.speedify.data.entities.forwardingagencies.memberships.roles.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}