package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.entities.locations.Country;
import de.seniorenheim.speedify.data.repositories.locations.CountryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CountryService {
    
    private final CountryRepository countryRepository;

    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    public Country getById(long id) {
        return countryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


}
