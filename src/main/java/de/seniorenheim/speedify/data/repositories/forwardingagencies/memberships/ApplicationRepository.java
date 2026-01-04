package de.seniorenheim.speedify.data.repositories.forwardingagencies.memberships;

import de.seniorenheim.speedify.data.entities.forwardingagencies.memberships.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findAllByUser_Id(Long userId);

    List<Application> findAllByForwardingAgency_Id(Long forwardingAgencyId);
}