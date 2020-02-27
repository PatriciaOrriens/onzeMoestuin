package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.ArrayList;

/**
 * @author Eric van Dalen
 * Repository interface for Plant
 */
public interface PlantRepository extends JpaRepository<Plant, Integer> {
    ArrayList<Plant> findAllByGarden(Garden garden);

    ArrayList<Plant> findAllByGardenAndStartDateIsNull(Garden garden);

    ArrayList<Plant> findAllByGardenAndStartDateIsNotNullAndHarvestDateIsNull(Garden garden);




}
