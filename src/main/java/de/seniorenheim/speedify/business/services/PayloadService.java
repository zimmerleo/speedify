package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.entities.jobs.Payload;
import de.seniorenheim.speedify.data.repositories.jobs.PayloadRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PayloadService {

    private final PayloadRepository payloadRepository;

    public List<Payload> getAll() {
        return payloadRepository.findAll();
    }

    public Payload getById(long id) {
        return payloadRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
