package de.seniorenheim.speedify.data.repositories.locations;

import de.seniorenheim.speedify.data.entities.locations.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findCitiesByLocatedInIdEquals(Long locatedIn);
}