package de.seniorenheim.speedify.data.repositories.forwardingagencies;

import de.seniorenheim.speedify.data.entities.forwardingagencies.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
}