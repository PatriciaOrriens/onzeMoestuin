package groentjes.onzeMoestuin.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Eric van Dalen and Gjalt Wybenga
 * The Taskplant class concerns tasks to be performed for specific plants
 */
@Entity
public class TaskPlant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskPlantId;

    private Date dueDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plantId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Plant plant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "taskPlantInfoId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TaskPlantInfo taskPlantInfo;

    public Integer getTaskPlantId() {
        return taskPlantId;
    }

    public void setTaskPlantId(Integer taskPlantId) {
        this.taskPlantId = taskPlantId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public TaskPlantInfo getTaskPlantInfo() {
        return taskPlantInfo;
    }

    public void setTaskPlantInfo(TaskPlantInfo taskPlantInfo) {
        this.taskPlantInfo = taskPlantInfo;
    }
}
