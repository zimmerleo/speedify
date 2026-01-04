package de.seniorenheim.speedify.presentation;

import de.seniorenheim.speedify.business.services.ApplicationService;
import de.seniorenheim.speedify.business.services.ForwardingAgencyService;
import de.seniorenheim.speedify.business.services.JobService;
import de.seniorenheim.speedify.business.services.MembershipService;
import de.seniorenheim.speedify.business.util.EntityMapper;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.ForwardingAgencyCreationDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.ForwardingAgencyResponseDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships.ApplicationCreationDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships.ApplicationResponseDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships.MembershipCreationDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships.MembershipResponseDto;
import de.seniorenheim.speedify.data.dtos.jobs.JobResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/forwardingagencies")
@AllArgsConstructor
public class ForwardingAgencyController {

    private final ForwardingAgencyService forwardingAgencyService;
    private final ApplicationService applicationService;
    private final MembershipService membershipService;
    private final JobService jobService;
    private final EntityMapper entityMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ForwardingAgencyResponseDto> getAll() {
        return forwardingAgencyService.getAll().stream().map(entityMapper::fromEntity).toList();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ForwardingAgencyResponseDto getById(@PathVariable Long id) {
        return entityMapper.fromEntity(forwardingAgencyService.getById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody ForwardingAgencyCreationDto forwardingAgencyCreationDto) {
        forwardingAgencyService.save(forwardingAgencyCreationDto);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable long id, @RequestBody ForwardingAgencyCreationDto forwardingAgencyCreationDto) {
        forwardingAgencyService.update(id, forwardingAgencyCreationDto);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable long id) {
        forwardingAgencyService.delete(id);
    }



    @GetMapping(value = "{id}/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JobResponseDto> getJobs(@PathVariable Long id) {
        return jobService.getAllByForwardingAgencyId(id).stream().map(entityMapper::fromEntity).toList();
    }



    @GetMapping(value = "{id}/applications", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ApplicationResponseDto> getApplications(@PathVariable Long id) {
        return applicationService.getAllByForwardingAgencyId(id).stream().map(entityMapper::fromEntity).toList();
    }

    @PostMapping(value = "{id}/applications", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@PathVariable Long id, @RequestBody ApplicationCreationDto applicationCreationDto) {
        applicationService.save(id, applicationCreationDto);
    }

    @DeleteMapping(value = "{id}/applications/{applicationId}")
    public void deleteApplication(@PathVariable Long id, @PathVariable Long applicationId) {
        applicationService.delete(applicationId);
    }



    @GetMapping(value = "{id}/memberships", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MembershipResponseDto> getMemberships(@PathVariable Long id) {
        return membershipService.getAllByForwardingAgencyId(id).stream().map(entityMapper::fromEntity).toList();
    }

    @PostMapping(value = "{id}/memberships", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@PathVariable Long id,  @RequestBody MembershipCreationDto membershipCreationDto) {
        membershipService.save(id, membershipCreationDto);
    }

    @PostMapping(value = "{id}/memberships/{membershipId}/end")
    public void end(@PathVariable Long id, @PathVariable Long membershipId) {
        membershipService.end(membershipId);
    }

    @DeleteMapping(value = "{id}/memberships/{membershipId}")
    public void deleteMembership(@PathVariable Long id, @PathVariable Long membershipId) {
        membershipService.delete(membershipId);
    }
}
