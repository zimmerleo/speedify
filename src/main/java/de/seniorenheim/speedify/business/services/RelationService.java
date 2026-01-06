package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.dtos.forwardingagencies.relations.RelationCreationDto;
import de.seniorenheim.speedify.data.entities.forwardingagencies.relations.Relation;
import de.seniorenheim.speedify.data.repositories.forwardingagencies.ForwardingAgencyRepository;
import de.seniorenheim.speedify.data.repositories.forwardingagencies.relations.RelationRepository;
import de.seniorenheim.speedify.data.repositories.forwardingagencies.relations.RelationTypeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class RelationService {

    private final RelationRepository relationRepository;
    private final ForwardingAgencyRepository forwardingAgencyRepository;
    private final RelationTypeRepository relationTypeRepository;

    public List<Relation> getAll() {
        return relationRepository.findAll();
    }

    public List<Relation> getAllByForwardingAgencyId(Long forwardingAgencyId) {
        return getAll().stream().filter(relation -> relation.getForwardingAgency_1().getId() == forwardingAgencyId || relation.getForwardingAgency_2().getId() == forwardingAgencyId).toList();
    }

    @Transactional
    public void save(Long forwardingAgency1, RelationCreationDto relationCreationDto) {
        Relation relation = Relation.builder()
                .forwardingAgency_1(forwardingAgencyRepository.getReferenceById(forwardingAgency1))
                .forwardingAgency_2(forwardingAgencyRepository.getReferenceById(relationCreationDto.getForwardingAgency_2()))
                .relationType(relationTypeRepository.getReferenceById(relationCreationDto.getRelationType()))
                .build();
        relationRepository.save(relation);
    }

    @Transactional
    public void delete(Long forwardingAgency1, Long forwardingAgency2) {
        getAll().stream().filter(relation -> relation.getForwardingAgency_1().getId().equals(forwardingAgency1) && relation.getForwardingAgency_2().getId().equals(forwardingAgency2)).findFirst().ifPresent(relationRepository::delete);
    }
}
