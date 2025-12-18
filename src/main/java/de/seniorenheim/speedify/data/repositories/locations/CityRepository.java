package de.seniorenheim.speedify.data.repositories.locations;

import de.seniorenheim.speedify.data.entities.locations.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}