package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.PlantInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlantInformationRepository extends JpaRepository<PlantInformation, Integer> {
    Optional<PlantInformation> findByPlantName(String plantName);
}
