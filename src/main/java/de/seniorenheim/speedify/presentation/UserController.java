package de.seniorenheim.speedify.presentation;

import de.seniorenheim.speedify.business.services.*;
import de.seniorenheim.speedify.business.util.EntityMapper;
import de.seniorenheim.speedify.data.dtos.finance.TransactionResponseDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships.ApplicationResponseDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships.MembershipResponseDto;
import de.seniorenheim.speedify.data.dtos.trucks.TruckCreationDto;
import de.seniorenheim.speedify.data.dtos.trucks.TruckResponseDto;
import de.seniorenheim.speedify.data.dtos.users.UserCreationDto;
import de.seniorenheim.speedify.data.dtos.users.UserResponseDto;
import de.seniorenheim.speedify.data.dtos.users.UserSpecializationResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final TruckService truckService;
    private final MembershipService membershipService;
    private final ApplicationService applicationService;
    private final UserSpecializationService userSpecializationService;
    private final EntityMapper entityMapper;
    private final TransactionService transactionService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream().map(entityMapper::fromEntity).toList();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or #id == @authUtils.getCurrentUser().user.id")
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponseDto getById(@PathVariable Long id) {
        return entityMapper.fromEntity(userService.getById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody UserCreationDto userCreationDto) {
        userService.save(userCreationDto);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or #id == @authUtils.getCurrentUser().user.id")
    @PatchMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable long id, @RequestBody UserCreationDto userCreationDto) {
        userService.update(id, userCreationDto);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN') or #id == @authUtils.getCurrentUser().user.id")
    @GetMapping(value = "{id}/trucks", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TruckResponseDto> getTrucks(@PathVariable Long id) {
        return truckService.getAllByUserId(id).stream().map(entityMapper::fromEntity).toList();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or #id == @authUtils.getCurrentUser().user.id")
    @GetMapping(value = "{id}/trucks/{truckId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TruckResponseDto getTruckById(@PathVariable Long id, @PathVariable Long truckId) {
        return entityMapper.fromEntity(truckService.getById(id, truckId));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or #id == @authUtils.getCurrentUser().user.id")
    @PostMapping(value = "{id}/trucks", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveTruck(@PathVariable Long id, @RequestBody TruckCreationDto truckDto) {
        truckService.save(id, truckDto);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or #id == @authUtils.getCurrentUser().user.id")
    @PatchMapping(value = "{id}/trucks/{truckId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateTruck(@PathVariable long id, @PathVariable Long truckId, @RequestBody TruckCreationDto truckDto) {
        truckService.update(id, truckId, truckDto);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or #id == @authUtils.getCurrentUser().user.id")
    @DeleteMapping(value = "{id}/trucks/{truckId}")
    public void deleteTruck(@PathVariable long id, @PathVariable Long truckId) {
        truckService.delete(id, truckId);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN') or #id == @authUtils.getCurrentUser().user.id")
    @GetMapping(value = "{id}/applications", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ApplicationResponseDto> getApplications(@PathVariable Long id) {
        return applicationService.getAllByUserId(id).stream().map(entityMapper::fromEntity).toList();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or #id == @authUtils.getCurrentUser().user.id")
    @DeleteMapping(value = "{id}/applications/{applicationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteApplication(@PathVariable Long id, @PathVariable Long applicationId) {
        applicationService.deleteByUser(id, applicationId);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN') or #id == @authUtils.getCurrentUser().user.id")
    @GetMapping(value = "{id}/memberships", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MembershipResponseDto> getMemberships(@PathVariable Long id) {
        return membershipService.getAllByUserId(id).stream().map(entityMapper::fromEntity).toList();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or #id == @authUtils.getCurrentUser().user.id")
    @GetMapping(value = "{id}/memberships/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public MembershipResponseDto getCurrentMembership(@PathVariable Long id) {
        return entityMapper.fromEntity(membershipService.getCurrentMembership(id));
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') || #id == @authUtils.getCurrentUser().user.id")
    @PatchMapping(value = "{id}/memberships/{membershipId}")
    public void endMembership(@PathVariable Long id, @PathVariable Long membershipId) {
        membershipService.endByUser(id, membershipId);
    }


    @PreAuthorize("hasAuthority('ROLE_ADMIN') or #id == @authUtils.getCurrentUser().user.id")
    @GetMapping(value = "{id}/specializations", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserSpecializationResponseDto> getSpecializations(@PathVariable Long id) {
        return userSpecializationService.getAllByUserId(id).stream().map(entityMapper::fromEntity).toList();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') or #id == @authUtils.getCurrentUser().user.id")
    @GetMapping(value = "{id}/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TransactionResponseDto> getTransactions(@PathVariable Long id) {
        return transactionService.getAllByUserId(id).stream().map(entityMapper::fromEntity).toList();
    }
}
