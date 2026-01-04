package de.seniorenheim.speedify.data.repositories.users;

import de.seniorenheim.speedify.data.entities.users.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {
    Optional<Rank> findByXpNeeded(Integer xpNeeded);
}