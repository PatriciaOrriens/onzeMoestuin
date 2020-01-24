package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.Plant;
import groentjes.onzeMoestuin.model.TaskPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.ArrayList;

/**
 * @author Eric van Dalen and Gjalt Wybenga
 * Repository interface for TaskPlant
 */
public interface TaskPlantRepository extends JpaRepository<TaskPlant, Integer> {
    ArrayList<TaskPlant> findAllByPlant(Plant plant);
}
