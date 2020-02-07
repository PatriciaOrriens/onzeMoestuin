package groentjes.onzeMoestuin.model;

/**
 * @author Eric van Dalen
 * abstract builder class
 */
abstract class Builder {

    protected TaskGarden taskGarden;

    public void createNewTaskGarden() {
        this.taskGarden = new TaskGarden();
    }

    public TaskGarden getTaskGarden() {
        return this.taskGarden;
    }

    public abstract Builder buildTaskGardenName(String taskGardenName);
    public abstract Builder buildDueDate(String dueDate);
    public abstract Builder buildLinkToGarden(Garden garden);
}
