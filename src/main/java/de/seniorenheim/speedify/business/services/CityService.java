package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.entities.locations.City;
import de.seniorenheim.speedify.data.repositories.locations.CityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final CountryService countryService;
    private final DLCService dlcService;

    public List<City> getAll() {
        return cityRepository.findAll();
    }

    public City getById(long id) {
        return cityRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<City> getAllByCountry(long id) {
        return cityRepository.findCitiesByLocatedInIdEquals(id);
    }


}
