package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
