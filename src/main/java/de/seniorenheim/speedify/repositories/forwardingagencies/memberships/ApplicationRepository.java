package de.seniorenheim.speedify.repositories.forwardingagencies.memberships;

import de.seniorenheim.speedify.entities.forwardingagencies.memberships.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
}