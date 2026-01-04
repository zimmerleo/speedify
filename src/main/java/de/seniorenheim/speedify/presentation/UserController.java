package de.seniorenheim.speedify.presentation;

import de.seniorenheim.speedify.business.services.*;
import de.seniorenheim.speedify.business.util.EntityMapper;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships.ApplicationResponseDto;
import de.seniorenheim.speedify.data.dtos.forwardingagencies.memberships.MembershipResponseDto;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserResponseDto> getAll() {
        return userService.getAll().stream().map(entityMapper::fromEntity).toList();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponseDto getById(@PathVariable Long id) {
        return entityMapper.fromEntity(userService.getById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody UserCreationDto userCreationDto) {
        userService.save(userCreationDto);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable long id, @RequestBody UserCreationDto userCreationDto) {
        userService.update(id, userCreationDto);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }



    @GetMapping(value = "{id}/trucks", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TruckResponseDto> getTrucks(@PathVariable Long id) {
        return truckService.getAllByUserId(id).stream().map(entityMapper::fromEntity).toList();
    }



    @GetMapping(value = "{id}/applications", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ApplicationResponseDto> getApplications(@PathVariable Long id) {
        return applicationService.getAllByUserId(id).stream().map(entityMapper::fromEntity).toList();
    }



    @GetMapping(value = "{id}/memberships", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MembershipResponseDto> getMemberships(@PathVariable Long id) {
        return membershipService.getAllByUserId(id).stream().map(entityMapper::fromEntity).toList();
    }

    @GetMapping(value = "{id}/memberships/current", produces = MediaType.APPLICATION_JSON_VALUE)
    public MembershipResponseDto getCurrentMembership(@PathVariable Long id) {
        return entityMapper.fromEntity(membershipService.getCurrentMembership(id));
    }



    @GetMapping(value = "{id}/specializations", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserSpecializationResponseDto> getSpecializations(@PathVariable Long id) {
        return userSpecializationService.getAllByUserId(id).stream().map(entityMapper::fromEntity).toList();
    }
}
