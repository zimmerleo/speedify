package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.entities.forwardingagencies.Level;
import de.seniorenheim.speedify.data.repositories.forwardingagencies.LevelRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class LevelService {

    private final LevelRepository levelRepository;

    public List<Level> getAll() {
        return levelRepository.findAll();
    }

    public Level getById(Long id) {
        return levelRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Level getFirst() {
        return levelRepository.findByXpNeeded(0).orElseThrow(EntityNotFoundException::new);
    }
}
