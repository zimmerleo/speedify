package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.business.util.AuthenticationUtils;
import de.seniorenheim.speedify.data.dtos.users.UserCreationDto;
import de.seniorenheim.speedify.data.entities.forwardingagencies.memberships.Membership;
import de.seniorenheim.speedify.data.entities.users.LoginUser;
import de.seniorenheim.speedify.data.entities.users.User;
import de.seniorenheim.speedify.data.repositories.users.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BankAccountService bankAccountService;
    private final DLCService dlcService;
    private final UserSpecializationService userSpecializationService;
    private final MembershipService membershipService;
    private final ApplicationService applicationService;
    private final PasswordEncoder passwordEncoder = passwordEncoder();

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
                .password(passwordEncoder.encode(userDto.getPassword()))
                .bankAccount(bankAccountService.createUserAccount())
                .dlcs(new ArrayList<>(userDto.getDlcs().stream().map(dlcService::getById).toList()))
                .build();
        entity = userRepository.save(entity);
        userSpecializationService.createUserSpecializations(entity);
    }

    @Transactional
    public void update(long id, UserCreationDto userDto) {
        LoginUser loginUser = AuthenticationUtils.getCurrentUser();

        if (!loginUser.getUser().getId().equals(id) && loginUser.getUser().getAdministrator().equals(Boolean.FALSE)) {
            throw new AccessDeniedException("Access denied");
        }
        User entity = getById(id);
        entity.setName(userDto.getName());
        entity.setEmail(userDto.getEmail());
        entity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        entity.setDlcs(new ArrayList<>(userDto.getDlcs().stream().map(dlcService::getById).toList()));
        userRepository.save(entity);
    }

    @Transactional
    public void delete(long id) {
        userSpecializationService.getAllByUserId(id).forEach(userSpecializationService::delete);
        membershipService.getAllByUserId(id).forEach(membership -> membershipService.delete(membership.getId()));
        applicationService.getAllByUserId(id).forEach(application -> applicationService.delete(application.getId()));
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        Membership membership = membershipService.getCurrentMembership(user.getId());
        return new LoginUser(user, membership != null ? membership.getRole() : null);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
