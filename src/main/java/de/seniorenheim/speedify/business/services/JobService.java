package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.dtos.jobs.JobCreationDto;
import de.seniorenheim.speedify.data.entities.jobs.Job;
import de.seniorenheim.speedify.data.repositories.jobs.JobRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final TruckService truckService;
    private final PayloadService payloadService;
    private final CompanyService companyService;

    public List<Job> getAll() {
        return jobRepository.findAll();
    }

    public Job getById(long id) {
        return jobRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void save(JobCreationDto jobDto) {
        Job entity = Job.builder()
                .payload(payloadService.getById(jobDto.getPayload()))
                .origin(companyService.getById(jobDto.getOrigin()))
                .destination(companyService.getById(jobDto.getDestination()))
                .accepted(jobDto.getAccepted())
                .truck(truckService.getById(jobDto.getTruck()))
                .kilometersDriven(jobDto.getKilometersDriven())
                .hoursDriven(jobDto.getHoursDriven())
                .completed(jobDto.getCompleted())
                .build();
        jobRepository.save(entity);
    }

    @Transactional
    public void update(long id, JobCreationDto jobDto) {
        Job entity = getById(id);
        entity.setPayload(payloadService.getById(jobDto.getPayload()));
        entity.setOrigin(companyService.getById(jobDto.getOrigin()));
        entity.setDestination(companyService.getById(jobDto.getDestination()));
        entity.setAccepted(jobDto.getAccepted());
        entity.setTruck(truckService.getById(jobDto.getTruck()));
        entity.setKilometersDriven(jobDto.getKilometersDriven());
        entity.setHoursDriven(jobDto.getHoursDriven());
        entity.setCompleted(jobDto.getCompleted());
        jobRepository.save(entity);
    }

    @Transactional
    public void delete(long id) {
        jobRepository.deleteById(id);
    }
}
