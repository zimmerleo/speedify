package de.seniorenheim.speedify.repositories.dlcs;

import de.seniorenheim.speedify.entities.dlcs.DLC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DLCRepository extends JpaRepository<DLC, Long> {
}