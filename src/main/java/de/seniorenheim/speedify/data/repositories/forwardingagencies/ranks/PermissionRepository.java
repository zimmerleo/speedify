package de.seniorenheim.speedify.data.repositories.forwardingagencies.ranks;

import de.seniorenheim.speedify.data.entities.forwardingagencies.ranks.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}