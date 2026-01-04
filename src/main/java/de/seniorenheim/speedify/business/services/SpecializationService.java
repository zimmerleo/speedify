package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.entities.users.Specialization;
import de.seniorenheim.speedify.data.repositories.users.SpecializationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class SpecializationService {

    private SpecializationRepository specializationRepository;

    public List<Specialization> getAll() {
        return specializationRepository.findAll();
    }

    public Specialization getById(Long id) {
        return specializationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
