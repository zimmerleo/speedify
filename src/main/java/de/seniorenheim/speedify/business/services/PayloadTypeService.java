package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.entities.jobs.PayloadType;
import de.seniorenheim.speedify.data.repositories.jobs.PayloadTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PayloadTypeService {

    private final PayloadTypeRepository payloadTypeRepository;

    public List<PayloadType> getAll() {
        return payloadTypeRepository.findAll();
    }

    public PayloadType getById(long id) {
        return payloadTypeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
