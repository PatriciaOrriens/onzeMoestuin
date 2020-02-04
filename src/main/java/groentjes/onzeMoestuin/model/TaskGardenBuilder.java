package groentjes.onzeMoestuin.model;

public class TaskGardenBuilder extends Builder {

    public TaskGardenBuilder buildTaskGardenName(String taskGardenName) {
        this.taskGarden.setTaskGardenName(taskGardenName);
        return this;
    }

    public TaskGardenBuilder buildDueDate(String dueDate) {
        this.taskGarden.setDueDate(dueDate);
        return this;
    }
}
