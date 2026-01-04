package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.entities.forwardingagencies.relations.Relation;
import de.seniorenheim.speedify.data.repositories.forwardingagencies.relations.RelationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class RelationService {

    private final RelationRepository relationRepository;

    public List<Relation> getAll() {
        return relationRepository.findAll();
    }
}
