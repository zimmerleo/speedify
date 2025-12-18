package de.seniorenheim.speedify.repositories.forwardingagencies.ranks;

import de.seniorenheim.speedify.entities.forwardingagencies.ranks.Rank_Permission;
import de.seniorenheim.speedify.idclasses.Rank_Permission_Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Rank_PermissionRepository extends JpaRepository<Rank_Permission, Rank_Permission_Id> {
}