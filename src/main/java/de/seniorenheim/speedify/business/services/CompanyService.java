package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.entities.locations.Company;
import de.seniorenheim.speedify.data.repositories.locations.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CompanyService {
    
    private final CompanyRepository companyRepository;
    private final CityService cityService;

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company getById(long id) {
        return companyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
