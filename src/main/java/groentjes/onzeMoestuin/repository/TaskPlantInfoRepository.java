package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.PlantInformation;
import groentjes.onzeMoestuin.model.TaskPlantInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface TaskPlantInfoRepository extends JpaRepository<TaskPlantInfo, Integer> {
    ArrayList<TaskPlantInfo> findAllByPlantInformation(PlantInformation plantInformation);
}
