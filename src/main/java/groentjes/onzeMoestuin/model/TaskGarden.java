package groentjes.onzeMoestuin.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Eric van Dalen
 * This class concerns tasks for the whole garden
 */
@Entity
public class TaskGarden extends Task {

    private String taskGardenName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "garden_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Garden garden;

    public String getTaskGardenName() {
        return taskGardenName;
    }

    public void setTaskGardenName(String taskGardenName) {
        this.taskGardenName = taskGardenName;
    }

    public Garden getGarden() {
        return garden;
    }

    public void setGarden(Garden garden) {
        this.garden = garden;
    }
}
