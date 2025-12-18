package de.seniorenheim.speedify.repositories.users;

import de.seniorenheim.speedify.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}