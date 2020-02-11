package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Eric van Dalen
 * Repository interface for Task
 */
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
