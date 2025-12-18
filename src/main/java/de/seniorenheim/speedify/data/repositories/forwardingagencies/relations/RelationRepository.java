package de.seniorenheim.speedify.data.repositories.forwardingagencies.relations;

import de.seniorenheim.speedify.data.entities.forwardingagencies.relations.Relation;
import de.seniorenheim.speedify.data.idclasses.RelationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationRepository extends JpaRepository<Relation, RelationId> {
}