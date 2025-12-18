package de.seniorenheim.speedify.data.repositories.forwardingagencies.ranks;

import de.seniorenheim.speedify.data.entities.forwardingagencies.ranks.ForwardingAgency_Rank;
import de.seniorenheim.speedify.data.idclasses.ForwardingAgency_Rank_Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForwardingAgency_RankRepository extends JpaRepository<ForwardingAgency_Rank, ForwardingAgency_Rank_Id> {
}