package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.PlantInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantInformationRepository extends JpaRepository<PlantInformation, Integer> {
}
