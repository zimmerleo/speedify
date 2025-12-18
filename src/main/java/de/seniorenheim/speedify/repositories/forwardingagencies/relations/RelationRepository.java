package de.seniorenheim.speedify.repositories.forwardingagencies.relations;

import de.seniorenheim.speedify.entities.forwardingagencies.relations.Relation;
import de.seniorenheim.speedify.idclasses.RelationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationRepository extends JpaRepository<Relation, RelationId> {
}