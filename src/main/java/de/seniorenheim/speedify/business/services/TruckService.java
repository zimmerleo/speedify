package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.business.util.AuthenticationUtils;
import de.seniorenheim.speedify.data.dtos.trucks.TruckCreationDto;
import de.seniorenheim.speedify.data.entities.trucks.Truck;
import de.seniorenheim.speedify.data.entities.users.LoginUser;
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

    public List<Truck> getAll() {
        return truckRepository.findAll();
    }

    public List<Truck> getAllByUserId(long id) {
        return truckRepository.findAllByOwner_Id(id);
    }

    public Truck getById(long id) {
        return truckRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    @Transactional
    public void save(TruckCreationDto truckDto) {
        LoginUser loginUser = AuthenticationUtils.getCurrentUser();

        Truck entity = Truck.builder()
                .licensePlate(truckDto.getLicensePlate())
                .type(truckTypeRepository.getReferenceById(truckDto.getType()))
                .owner(loginUser.getUser())
                .build();
        truckRepository.save(entity);
    }

    @Transactional
    public void update(long id, TruckCreationDto truckDto) {
        LoginUser loginUser = AuthenticationUtils.getCurrentUser();
        Truck entity = getById(id);
        if (!entity.getOwner().equals(loginUser.getUser())) return;

        if (truckDto.getLicensePlate() != null) entity.setLicensePlate(truckDto.getLicensePlate());
        if (truckDto.getType() != null) entity.setType(truckTypeRepository.getReferenceById(truckDto.getType()));
        truckRepository.save(entity);
    }

    @Transactional
    public void delete(long id) {
        LoginUser loginUser = AuthenticationUtils.getCurrentUser();
        Truck entity = getById(id);
        if (!entity.getOwner().equals(loginUser.getUser())) return;

        truckRepository.delete(entity);
    }
}
