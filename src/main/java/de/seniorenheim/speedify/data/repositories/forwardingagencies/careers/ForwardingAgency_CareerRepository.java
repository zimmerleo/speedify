package de.seniorenheim.speedify.data.repositories.forwardingagencies.careers;

import de.seniorenheim.speedify.data.entities.forwardingagencies.careers.ForwardingAgency_Career;
import de.seniorenheim.speedify.data.idclasses.ForwardingAgency_Career_Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForwardingAgency_CareerRepository extends JpaRepository<ForwardingAgency_Career, ForwardingAgency_Career_Id> {
}