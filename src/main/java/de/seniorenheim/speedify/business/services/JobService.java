package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.dtos.jobs.JobCreationDto;
import de.seniorenheim.speedify.data.entities.jobs.Job;
import de.seniorenheim.speedify.data.repositories.jobs.JobRepository;
import de.seniorenheim.speedify.data.repositories.jobs.PayloadRepository;
import de.seniorenheim.speedify.data.repositories.locations.CompanyRepository;
import de.seniorenheim.speedify.data.repositories.trucks.TruckRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final PayloadRepository payloadRepository;
    private final TruckRepository truckRepository;
    private final CompanyRepository companyRepository;
    private final UserSpecializationService  userSpecializationService;
    private final ForwardingAgencyService forwardingAgencyService;

    public List<Job> getAll() {
        return jobRepository.findAll();
    }

    public List<Job> getAllByForwardingAgencyId(Long id) {
        return jobRepository.findAllByForwardingAgencyId(id);
    }

    public Job getById(long id) {
        return jobRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void save(JobCreationDto jobDto) {
        Job entity = Job.builder()
                .payload(payloadRepository.getReferenceById(jobDto.getPayload()))
                .origin(companyRepository.getReferenceById(jobDto.getOrigin()))
                .destination(companyRepository.getReferenceById(jobDto.getDestination()))
                .accepted(jobDto.getAccepted() != null ? jobDto.getAccepted() : null)
                .truck(jobDto.getTruck() != null ? truckRepository.getReferenceById(jobDto.getTruck()) : null)
                .kilometersDriven(jobDto.getKilometersDriven() != null ?  jobDto.getKilometersDriven() : BigDecimal.ZERO)
                .hoursDriven(jobDto.getHoursDriven() != null ?  jobDto.getHoursDriven() : BigDecimal.ZERO)
                .completed(jobDto.getCompleted() !=  null ? jobDto.getCompleted() : null)
                .xp(jobDto.getXp() != null ? jobDto.getXp() : 0)
                .build();
        entity = jobRepository.save(entity);
        if (entity.getXp() != 0) {
            userSpecializationService.applyXP(entity);
            forwardingAgencyService.applyXP(entity);
        }
    }

    @Transactional
    public void update(long id, JobCreationDto jobDto) {
        Job entity = getById(id);
        if (jobDto.getPayload() != null) {
            entity.setPayload(payloadRepository.getReferenceById(jobDto.getPayload()));
        }
        if (jobDto.getOrigin() != null) {
            entity.setOrigin(companyRepository.getReferenceById(jobDto.getOrigin()));
        }
        if (jobDto.getDestination() != null) {
            entity.setDestination(companyRepository.getReferenceById(jobDto.getDestination()));
        }
        if (jobDto.getAccepted() != null && entity.getAccepted() == null) {
            entity.setAccepted(jobDto.getAccepted());
        }
        if (jobDto.getTruck() != null && entity.getTruck() == null) {
            entity.setTruck(truckRepository.getReferenceById(jobDto.getTruck()));
        }
        if (jobDto.getKilometersDriven() != null && entity.getKilometersDriven().compareTo(BigDecimal.ZERO) == 0) {
            entity.setKilometersDriven(jobDto.getKilometersDriven());
        }
        if (jobDto.getHoursDriven() != null && entity.getHoursDriven().compareTo(BigDecimal.ZERO) == 0) {
            entity.setHoursDriven(jobDto.getHoursDriven());
        }
        if (jobDto.getCompleted() != null && entity.getCompleted() == null) {
            entity.setCompleted(jobDto.getCompleted());
        }
        if (jobDto.getXp() != null && entity.getXp() == 0) {
            entity.setXp(jobDto.getXp());
            userSpecializationService.applyXP(entity);
            forwardingAgencyService.applyXP(entity);
        }
        jobRepository.save(entity);
    }

    @Transactional
    public void delete(long id) {
        Job entity = getById(id);
        entity.setXp(-entity.getXp());
        userSpecializationService.applyXP(entity);
        forwardingAgencyService.applyXP(entity);
        jobRepository.deleteById(id);
    }
}
