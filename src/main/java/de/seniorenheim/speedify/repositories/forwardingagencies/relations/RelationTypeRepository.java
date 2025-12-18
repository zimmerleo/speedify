package de.seniorenheim.speedify.repositories.forwardingagencies.relations;

import de.seniorenheim.speedify.entities.forwardingagencies.relations.RelationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationTypeRepository extends JpaRepository<RelationType, Long> {
}