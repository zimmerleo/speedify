package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.entities.forwardingagencies.relations.RelationType;
import de.seniorenheim.speedify.data.repositories.forwardingagencies.relations.RelationTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class RelationTypeService {

    private final RelationTypeRepository relationTypeRepository;

    public List<RelationType> getAll() {
        return relationTypeRepository.findAll();
    }

    public RelationType getById(Long id) {
        return relationTypeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
