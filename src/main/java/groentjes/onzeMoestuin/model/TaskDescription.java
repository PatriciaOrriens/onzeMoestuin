package groentjes.onzeMoestuin.model;

import javax.persistence.*;

/**
 * @author Patricia Orriens-Spuij and Eric van Dalen
 * This class gives a description of tasks for plants in general
 */
@Entity
public class TaskDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskDescriptionId;

    @Column(unique = true)
    private String taskName;

    // getters and setters
    public Integer getTaskDescriptionId() {
        return taskDescriptionId;
    }

    public void setTaskDescriptionId(Integer taskDescriptionId) {
        this.taskDescriptionId = taskDescriptionId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
