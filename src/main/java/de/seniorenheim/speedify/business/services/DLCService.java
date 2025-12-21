package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.entities.dlcs.DLC;
import de.seniorenheim.speedify.data.repositories.dlcs.DLCRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class DLCService {

    private final DLCRepository dlcRepository;

    public List<DLC> getAll() {
        return dlcRepository.findAll();
    }

    public DLC getById(long id) {
        return dlcRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


}
