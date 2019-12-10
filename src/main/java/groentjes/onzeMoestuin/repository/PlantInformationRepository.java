package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.PlantInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantInformationRepository extends JpaRepository<PlantInformation, Integer> {
}
