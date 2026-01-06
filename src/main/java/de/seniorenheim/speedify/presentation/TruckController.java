package de.seniorenheim.speedify.presentation;

import de.seniorenheim.speedify.business.services.TruckService;
import de.seniorenheim.speedify.business.util.EntityMapper;
import de.seniorenheim.speedify.data.dtos.trucks.TruckCreationDto;
import de.seniorenheim.speedify.data.dtos.trucks.TruckResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trucks")
@AllArgsConstructor
public class TruckController {

    private final TruckService truckService;
    private final EntityMapper entityMapper;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TruckResponseDto> getAll() {
        return truckService.getAll().stream().map(entityMapper::fromEntity).toList();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TruckResponseDto getById(@PathVariable Long id) {
        return entityMapper.fromEntity(truckService.getById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody TruckCreationDto truckDto) {
        truckService.save(truckDto);
    }

    @PatchMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable long id, @RequestBody TruckCreationDto truckDto) {
        truckService.update(id, truckDto);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable long id) {
        truckService.delete(id);
    }
}
