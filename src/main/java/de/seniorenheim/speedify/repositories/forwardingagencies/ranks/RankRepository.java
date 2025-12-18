package de.seniorenheim.speedify.repositories.forwardingagencies.ranks;

import de.seniorenheim.speedify.entities.forwardingagencies.ranks.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {
}