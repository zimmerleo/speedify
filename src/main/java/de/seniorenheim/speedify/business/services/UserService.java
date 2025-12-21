package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.dtos.users.UserCreationDto;
import de.seniorenheim.speedify.data.entities.users.User;
import de.seniorenheim.speedify.data.repositories.users.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BankAccountService bankAccountService;
    private final DLCService dLCService;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void save(UserCreationDto userDto) {
        User entity = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword()) //TODO ENCRYPTING
                .bankAccount(bankAccountService.createUserAccount())
                .dlcs(userDto.getDlcs().stream().map(dLCService::getById).toList())
                .administrator(userDto.getAdministrator())
                .creationDate(LocalDateTime.now())
                .build();
        userRepository.save(entity);
    }

    @Transactional
    public void update(long id, UserCreationDto userDto) {
        User entity = getById(id);
        entity.setName(userDto.getName());
        entity.setEmail(userDto.getEmail());
        entity.setPassword(userDto.getPassword()); //TODO ENCRYPTING
        entity.setDlcs(userDto.getDlcs().stream().map(dLCService::getById).toList());
        entity.setAdministrator(userDto.getAdministrator());
        userRepository.save(entity);
    }

    @Transactional
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
