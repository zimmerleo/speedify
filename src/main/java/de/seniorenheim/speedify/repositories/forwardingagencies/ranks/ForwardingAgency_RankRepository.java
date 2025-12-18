package de.seniorenheim.speedify.repositories.forwardingagencies.ranks;

import de.seniorenheim.speedify.entities.forwardingagencies.ranks.ForwardingAgency_Rank;
import de.seniorenheim.speedify.idclasses.ForwardingAgency_Rank_Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForwardingAgency_RankRepository extends JpaRepository<ForwardingAgency_Rank, ForwardingAgency_Rank_Id> {
}