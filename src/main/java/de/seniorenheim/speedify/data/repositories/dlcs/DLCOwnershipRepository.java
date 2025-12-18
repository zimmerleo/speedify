package de.seniorenheim.speedify.data.repositories.dlcs;

import de.seniorenheim.speedify.data.entities.dlcs.DLCOwnership;
import de.seniorenheim.speedify.data.idclasses.DLCOwnershipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DLCOwnershipRepository extends JpaRepository<DLCOwnership, DLCOwnershipId> {
}