package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.entities.users.Rank;
import de.seniorenheim.speedify.data.repositories.users.RankRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class RankService {

    private final RankRepository rankRepository;

    public List<Rank> getAll() {
        return rankRepository.findAll();
    }

    public Rank getById(Long id) {
        return rankRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Rank getFirst() {
        return rankRepository.findByXpNeeded(0).orElseThrow(EntityNotFoundException::new);
    }
}
