package groentjes.onzeMoestuin.model;

/**
 * @author Eric van Dalen
 * concrete builder class for TaskGarden
 */
public class TaskGardenBuilder extends Builder {

    public TaskGardenBuilder buildTaskGardenName(String taskGardenName) {
        this.taskGarden.setTaskGardenName(taskGardenName);
        return this;
    }

    public TaskGardenBuilder buildDueDate(String dueDate) {
        this.taskGarden.setDueDate(dueDate);
        return this;
    }

    public TaskGardenBuilder buildLinkToGarden(Garden garden) {
        this.taskGarden.setGarden(garden);
        return this;
    }
}
