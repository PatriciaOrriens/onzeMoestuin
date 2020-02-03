package groentjes.onzeMoestuin.model;

import javax.persistence.Entity;

/**
 * @author Eric van Dalen
 * This class concerns tasks for the whole garden
 */
@Entity
public class TaskGarden extends Task {

    private String taskGardenName;

    public String getTaskGardenName() {
        return taskGardenName;
    }

    public void setTaskGardenName(String taskGardenName) {
        this.taskGardenName = taskGardenName;
    }
}
