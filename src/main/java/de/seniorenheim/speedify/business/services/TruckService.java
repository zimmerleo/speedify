package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.dtos.trucks.TruckCreationDto;
import de.seniorenheim.speedify.data.entities.trucks.Truck;
import de.seniorenheim.speedify.data.repositories.trucks.TruckRepository;
import de.seniorenheim.speedify.data.repositories.trucks.TruckTypeRepository;
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
    private final TruckTypeRepository truckTypeRepository;
    private final UserService userService;

    public List<Truck> getAll() {
        return truckRepository.findAll();
    }

    public List<Truck> getAllByUserId(Long id) {
        return truckRepository.findAllByOwner_Id(id);
    }

    public Truck getById(Long userId, Long id) {
        return truckRepository.findByIdAndOwner_Id(id, userId).orElseThrow(EntityExistsException::new);
    }

    @Transactional
    public void save(Long userId, TruckCreationDto truckDto) {
        Truck entity = Truck.builder()
                .licensePlate(truckDto.getLicensePlate())
                .type(truckTypeRepository.getReferenceById(truckDto.getType()))
                .owner(userService.getById(userId))
                .build();
        truckRepository.save(entity);
    }

    @Transactional
    public void update(Long userId, Long truckId, TruckCreationDto truckDto) {
        Truck entity = getById(userId, truckId);
        if (!entity.getOwner().getId().equals(userId)) return;

        if (truckDto.getLicensePlate() != null) entity.setLicensePlate(truckDto.getLicensePlate());
        if (truckDto.getType() != null) entity.setType(truckTypeRepository.getReferenceById(truckDto.getType()));
        truckRepository.save(entity);
    }

    @Transactional
    public void delete(Long userId, Long truckId) {
        Truck toDelete = getById(userId, truckId);
        if (!toDelete.getOwner().getId().equals(userId)) return;
        truckRepository.delete(toDelete);
    }
}
