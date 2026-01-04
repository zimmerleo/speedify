package de.seniorenheim.speedify.business.services;

import de.seniorenheim.speedify.data.entities.jobs.Job;
import de.seniorenheim.speedify.data.entities.users.Specialization;
import de.seniorenheim.speedify.data.entities.users.User;
import de.seniorenheim.speedify.data.entities.users.UserSpecialization;
import de.seniorenheim.speedify.data.repositories.users.UserSpecializationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserSpecializationService {

    private final UserSpecializationRepository userSpecializationRepository;
    private final SpecializationService specializationService;
    private final RankService rankService;

    public List<UserSpecialization> getAllByUserId(Long id) {
        return userSpecializationRepository.findAllByUser_Id(id);
    }

    @Transactional
    public void createUserSpecializations(User user) {
        for (Specialization specialization : specializationService.getAll()) {
            userSpecializationRepository.save(UserSpecialization.builder()
                    .user(user)
                    .specialization(specialization)
                    .rank(rankService.getFirst())
                    .build());
        }
    }

    @Transactional
    public void applyXP(Job job) {
        UserSpecialization userSpecialization = userSpecializationRepository.findByUser_IdAndSpecialization_Id(job.getTruck().getOwner().getId(), job.getPayload().getPayloadType().getSpecialization().getId());

        int currentXp = userSpecialization.getXp();
        int xpNeeded = userSpecialization.getRank().getSuperior() != null ? userSpecialization.getRank().getSuperior().getXpNeeded() : Integer.MAX_VALUE;

        if (currentXp + job.getXp() >= xpNeeded) {
            userSpecialization.setRank(userSpecialization.getRank().getSuperior());
            userSpecialization.setXp(currentXp + job.getXp() - xpNeeded);
        } else {
            userSpecialization.setXp(currentXp + job.getXp());
        }
        userSpecializationRepository.save(userSpecialization);
    }

    @Transactional
    public void delete(UserSpecialization userSpecialization) {
        userSpecializationRepository.delete(userSpecialization);
    }
}
