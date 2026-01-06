package de.seniorenheim.speedify.data.repositories.jobs;

import de.seniorenheim.speedify.data.entities.jobs.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("""
    select distinct j
    from Job j
    join j.truck t
    join t.owner u
    join Membership m on m.user = u
    where m.forwardingAgency.id = :agencyId
    """)
    List<Job> findAllByForwardingAgencyId(Long agencyId);

    List<Job> findAllByCompletedAfter(LocalDateTime localDateTime);
}