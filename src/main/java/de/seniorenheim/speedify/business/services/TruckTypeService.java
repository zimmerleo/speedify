package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.entities.trucks.TruckType;
import de.seniorenheim.speedify.data.repositories.trucks.TruckTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class TruckTypeService {
    
    private final TruckTypeRepository truckTypeRepository;

    public List<TruckType> getAll() {
        return truckTypeRepository.findAll();
    }

    public TruckType getById(long id) {
        return truckTypeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
