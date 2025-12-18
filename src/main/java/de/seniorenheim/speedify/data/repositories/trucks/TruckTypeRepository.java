package de.seniorenheim.speedify.data.repositories.trucks;

import de.seniorenheim.speedify.data.entities.trucks.TruckType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckTypeRepository extends JpaRepository<TruckType, Long> {
}