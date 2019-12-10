package groentjes.onzeMoestuin.repository;

import groentjes.onzeMoestuin.model.Todo;
import groentjes.onzeMoestuin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
}
