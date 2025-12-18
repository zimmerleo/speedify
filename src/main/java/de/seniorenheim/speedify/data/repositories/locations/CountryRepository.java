package de.seniorenheim.speedify.data.repositories.locations;

import de.seniorenheim.speedify.data.entities.locations.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}