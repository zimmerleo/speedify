package de.seniorenheim.speedify.repositories.trucks;

import de.seniorenheim.speedify.entities.trucks.TruckType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckTypeRepository extends JpaRepository<TruckType, Long> {
}