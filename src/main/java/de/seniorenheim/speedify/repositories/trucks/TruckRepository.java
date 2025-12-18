package de.seniorenheim.speedify.repositories.trucks;

import de.seniorenheim.speedify.entities.trucks.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long> {
}