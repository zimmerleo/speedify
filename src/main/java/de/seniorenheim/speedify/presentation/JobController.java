package de.seniorenheim.speedify.presentation;

import de.seniorenheim.speedify.business.services.JobService;
import de.seniorenheim.speedify.business.util.EntityMapper;
import de.seniorenheim.speedify.data.dtos.jobs.JobCreationDto;
import de.seniorenheim.speedify.data.dtos.jobs.JobResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
@AllArgsConstructor
public class JobController {

    private final JobService jobService;
    private final EntityMapper entityMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JobResponseDto> getAll() {
        return jobService.getAll().stream().map(entityMapper::fromEntity).toList();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public JobResponseDto getById(@PathVariable Long id) {
        return entityMapper.fromEntity(jobService.getById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody JobCreationDto jobDto) {
        jobService.save(jobDto);
    }

    @PatchMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable long id, @RequestBody JobCreationDto jobDto) {
        jobService.update(id, jobDto);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable long id) {
        jobService.delete(id);
    }
}
