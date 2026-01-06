package de.seniorenheim.speedify.presentation;

import de.seniorenheim.speedify.business.services.*;
import de.seniorenheim.speedify.business.util.EntityMapper;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.ForwardingAgencyCreationDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.ForwardingAgencyResponseDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships.ApplicationCreationDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships.ApplicationResponseDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships.MembershipCreationDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships.MembershipResponseDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.relations.RelationCreationDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.relations.RelationResponseDto;
import de.seniorenheim.speedify.data.dtos.jobs.JobResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    private final RelationService relationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ForwardingAgencyResponseDto> getAll() {
        return forwardingAgencyService.getAll().stream().map(entityMapper::fromEntity).toList();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ForwardingAgencyResponseDto getById(@PathVariable Long id) {
        return entityMapper.fromEntity(forwardingAgencyService.getById(id));
    }

    @PreAuthorize("!@authUtils.hasAnyAgencyRole()")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> save(@RequestBody ForwardingAgencyCreationDto forwardingAgencyCreationDto) {
        forwardingAgencyService.save(forwardingAgencyCreationDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("@authUtils.isCLevel(#id)")
    @PatchMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable long id, @RequestBody ForwardingAgencyCreationDto forwardingAgencyCreationDto) {
        forwardingAgencyService.update(id, forwardingAgencyCreationDto);
    }

    @PreAuthorize("@authUtils.isChiefExecutiveOfficer(#id)")
    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable long id) {
        forwardingAgencyService.delete(id);
    }


    @PreAuthorize("@authUtils.isOperations(#id) || @authUtils.isQA(#id) || @authUtils.isChiefExecutiveOfficer(#id)")
    @GetMapping(value = "{id}/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<JobResponseDto> getJobs(@PathVariable Long id) {
        return jobService.getAllByForwardingAgencyId(id).stream().map(entityMapper::fromEntity).toList();
    }



    @PreAuthorize("@authUtils.isHR(#id) || @authUtils.isChiefExecutiveOfficer(#id)")
    @GetMapping(value = "{id}/applications", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ApplicationResponseDto> getApplications(@PathVariable Long id) {
        return applicationService.getAllByForwardingAgencyId(id).stream().map(entityMapper::fromEntity).toList();
    }

    @PreAuthorize("!@authUtils.isInAgency(#id)")
    @PostMapping(value = "{id}/applications", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveApplication(@PathVariable Long id, @RequestBody ApplicationCreationDto applicationCreationDto) {
        applicationService.save(id, applicationCreationDto);
    }

    @PreAuthorize("@authUtils.isHR(#id) || @authUtils.isChiefExecutiveOfficer(#id)")
    @DeleteMapping(value = "{id}/applications/{applicationId}")
    public void deleteApplication(@PathVariable Long id, @PathVariable Long applicationId) {
        applicationService.delete(applicationId);
    }



    @GetMapping(value = "{id}/memberships", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MembershipResponseDto> getMemberships(@PathVariable Long id) {
        return membershipService.getAllByForwardingAgencyId(id).stream().map(entityMapper::fromEntity).toList();
    }

    @PreAuthorize("@authUtils.isHR(#id) || @authUtils.isChiefExecutiveOfficer(#id)")
    @PostMapping(value = "{id}/memberships", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveMembership(@PathVariable Long id,  @RequestBody MembershipCreationDto membershipCreationDto) {
        membershipService.save(id, membershipCreationDto);
    }

    @PreAuthorize("@authUtils.isManager(#id) || @authUtils.isCLevel(#id)")
    @PatchMapping(value = "{id}/memberships/{membershipId}")
    public void endMembership(@PathVariable Long id, @PathVariable Long membershipId) {
        membershipService.end(membershipId);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping(value = "{id}/memberships/{membershipId}")
    public void deleteMembership(@PathVariable Long id, @PathVariable Long membershipId) {
        membershipService.delete(membershipId);
    }



    @GetMapping(value = "{id}/relations", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RelationResponseDto> getRelations(@PathVariable Long id) {
        return relationService.getAllByForwardingAgencyId(id).stream().map(entityMapper::fromEntity).toList();
    }

    @PreAuthorize("@authUtils.isChiefExecutiveOfficer(#id)")
    @PostMapping(value = "{id}/relations", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveRelation(@PathVariable Long id,  @RequestBody RelationCreationDto relationCreationDto) {
        relationService.save(id, relationCreationDto);
    }

    @PreAuthorize("@authUtils.isChiefExecutiveOfficer(#id)")
    @DeleteMapping(value = "{id}/relations/{forwardingAgencyId}")
    public void deleteRelation(@PathVariable Long id, @PathVariable Long forwardingAgencyId) {
        relationService.delete(id, forwardingAgencyId);
    }
}
