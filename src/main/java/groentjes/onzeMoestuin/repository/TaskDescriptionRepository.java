package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.TaskDescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDescriptionRepository extends JpaRepository<TaskDescription, Integer> {
}
