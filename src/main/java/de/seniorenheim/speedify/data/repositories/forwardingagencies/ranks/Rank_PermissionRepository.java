package de.seniorenheim.speedify.data.repositories.forwardingagencies.ranks;

import de.seniorenheim.speedify.data.entities.forwardingagencies.ranks.Rank_Permission;
import de.seniorenheim.speedify.data.idclasses.Rank_Permission_Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Rank_PermissionRepository extends JpaRepository<Rank_Permission, Rank_Permission_Id> {
}