package groentjes.onzeMoestuin.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * @author Patricia Orriens-Spuij
 * Task can be an abstract (super)class in the future, for the creation of tasks with a description or image.
 */
@Entity
public class TaskDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;

    @Column(unique = true)
    private String taskName;

    // getters and setters
    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
