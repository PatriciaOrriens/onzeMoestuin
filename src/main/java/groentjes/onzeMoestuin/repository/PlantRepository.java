package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant, Integer> {
}
