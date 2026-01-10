package de.seniorenheim.speedify.data.repositories.trucks;

import de.seniorenheim.speedify.data.entities.trucks.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long> {
    List<Truck> findAllByOwner_Id(Long ownerId);
    Optional<Truck> findByIdAndOwner_Id(Long id, Long ownerId);
}