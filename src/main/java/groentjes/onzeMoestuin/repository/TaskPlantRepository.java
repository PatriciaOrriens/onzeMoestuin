package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.Plant;
import groentjes.onzeMoestuin.model.TaskPlant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

/**
 * @author Eric van Dalen and Gjalt Wybenga
 * Repository interface for TaskPlant
 */
public interface TaskPlantRepository extends JpaRepository<TaskPlant, Integer> {
    ArrayList<TaskPlant> findAllByPlant(Plant plant);

    @Query("SELECT tp FROM TaskPlant tp WHERE tp.completedDate is null AND tp.plant=?1")
    ArrayList<TaskPlant> findNotCompletedTaskPlant(Plant plant);

}
