package de.seniorenheim.speedify.data.repositories.locations;

import de.seniorenheim.speedify.data.entities.locations.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}