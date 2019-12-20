package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.ArrayList;

/**
 * @author Eric van Dalen
 * Repository interface for Garden
 */
public interface GardenRepository extends JpaRepository<Garden, Integer> {
    ArrayList<Garden> findAllByUser(User user);
}
