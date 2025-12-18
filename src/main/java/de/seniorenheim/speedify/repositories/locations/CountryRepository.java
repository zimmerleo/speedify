package de.seniorenheim.speedify.repositories.locations;

import de.seniorenheim.speedify.entities.locations.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}