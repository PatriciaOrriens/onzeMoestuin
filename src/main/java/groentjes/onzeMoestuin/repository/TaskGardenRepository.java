package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.TaskGarden;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.ArrayList;

/**
 * @author Eric van Dalen
 * Repository interface for TaskGarden
 */
public interface TaskGardenRepository extends JpaRepository<TaskGarden, Integer> {
    ArrayList<TaskGarden> findAllByGarden(Garden garden);
}
