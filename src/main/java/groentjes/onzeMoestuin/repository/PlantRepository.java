package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.Garden;
import groentjes.onzeMoestuin.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface PlantRepository extends JpaRepository<Plant, Integer> {
    ArrayList<Plant> findAllByGarden(Optional<Garden> garden);

    ArrayList<Plant> findAllByGardenAndStartDateIsNotNull(Optional<Garden> garden);

    ArrayList<Plant> findAllByGardenAndStartDateIsNull(Optional<Garden> garden);


}
