package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.dtos.trucks.TruckCreationDto;
import de.seniorenheim.speedify.data.entities.trucks.Truck;
import de.seniorenheim.speedify.data.repositories.trucks.TruckRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class TruckService {

    private final TruckRepository truckRepository;
    private final UserService userService;
    private final TruckTypeService truckTypeService;

    public List<Truck> getAll() {
        return truckRepository.findAll();
    }

    public Truck getById(long id) {
        return truckRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    @Transactional
    public void save(TruckCreationDto truckDto) {
        Truck entity = Truck.builder()
                .licensePlate(truckDto.getLicensePlate())
                .type(truckTypeService.getById(truckDto.getType()))
                .owner(userService.getById(truckDto.getOwner()))
                .build();
        truckRepository.save(entity);
    }

    @Transactional
    public void update(long id, TruckCreationDto truckDto) {
        Truck entity = getById(id);
        entity.setLicensePlate(truckDto.getLicensePlate());
        entity.setType(truckTypeService.getById(truckDto.getType()));
        entity.setOwner(userService.getById(truckDto.getOwner()));
        truckRepository.save(entity);
    }

    @Transactional
    public void delete(long id) {
        truckRepository.deleteById(id);
    }
}
