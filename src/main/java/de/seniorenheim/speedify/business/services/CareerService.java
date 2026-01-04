package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.entities.forwardingagencies.memberships.roles.Career;
import de.seniorenheim.speedify.data.repositories.forwardingagencies.memberships.roles.CareerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class CareerService {

    private final CareerRepository careerRepository;

    public List<Career> getAll() {
        return careerRepository.findAll();
    }

    public Career getById(Long id) {
        return careerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
