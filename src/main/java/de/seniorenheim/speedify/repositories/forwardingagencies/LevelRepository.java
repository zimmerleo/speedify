package de.seniorenheim.speedify.repositories.forwardingagencies;

import de.seniorenheim.speedify.entities.forwardingagencies.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
}