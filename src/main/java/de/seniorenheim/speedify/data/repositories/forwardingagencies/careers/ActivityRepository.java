package de.seniorenheim.speedify.data.repositories.forwardingagencies.careers;

import de.seniorenheim.speedify.data.entities.forwardingagencies.careers.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
}