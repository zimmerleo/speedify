package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.entities.forwardingagencies.LegalForm;
import de.seniorenheim.speedify.data.repositories.forwardingagencies.LegalFormRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class LegalFormService {

    private final LegalFormRepository legalFormRepository;

    public List<LegalForm> getAll() {
        return legalFormRepository.findAll();
    }

    public LegalForm getById(Long id) {
        return legalFormRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
