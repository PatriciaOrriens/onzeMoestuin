package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.TaskPlant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Eric van Dalen and Gjalt Wybenga
 * Repository interface for TaskPlant
 */
public interface TaskPlantRepository extends JpaRepository<TaskPlant, Integer> {
}
