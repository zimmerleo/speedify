package de.seniorenheim.speedify.repositories.locations;

import de.seniorenheim.speedify.entities.locations.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}