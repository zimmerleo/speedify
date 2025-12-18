package de.seniorenheim.speedify.data.repositories.dlcs;

import de.seniorenheim.speedify.data.entities.dlcs.DLC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DLCRepository extends JpaRepository<DLC, Long> {
}