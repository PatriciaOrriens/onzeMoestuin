package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
