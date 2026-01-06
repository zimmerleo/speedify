package de.seniorenheim.speedify.data.repositories.forwardingagencies.memberships;

import de.seniorenheim.speedify.data.entities.forwardingagencies.memberships.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    Optional<Membership> findByUser_IdAndUntil(Long userId, LocalDate until);

    List<Membership> findAllByUser_Id(Long userId);

    List<Membership> findAllByForwardingAgency_Id(Long forwardingAgencyId);

    List<Membership> findAllByUntil(LocalDate until);
}