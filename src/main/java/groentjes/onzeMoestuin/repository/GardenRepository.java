package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.Garden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GardenRepository extends JpaRepository<Garden, Integer> {
}
