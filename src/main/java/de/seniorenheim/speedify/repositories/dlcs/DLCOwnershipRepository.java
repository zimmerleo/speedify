package de.seniorenheim.speedify.repositories.dlcs;

import de.seniorenheim.speedify.entities.dlcs.DLCOwnership;
import de.seniorenheim.speedify.idclasses.DLCOwnershipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DLCOwnershipRepository extends JpaRepository<DLCOwnership, DLCOwnershipId> {
}