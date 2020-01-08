package groentjes.onzeMoestuin.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;

@Entity
public class TaskPlantInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskPlantInfoId;

    private Integer daysAfterStart;
    private boolean repetitiveTask;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plantInfo_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PlantInformation plantInformation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Task task;

    public Integer getTaskPlantInfoId() {
        return taskPlantInfoId;
    }

    public void setTaskPlantInfoId(Integer taskPlantInfoId) {
        this.taskPlantInfoId = taskPlantInfoId;
    }

    public Integer getDaysAfterStart() {
        return daysAfterStart;
    }

    public void setDaysAfterStart(Integer daysAfterStart) {
        this.daysAfterStart = daysAfterStart;
    }

    public boolean isRepetitiveTask() {
        return repetitiveTask;
    }

    public void setRepetitiveTask(boolean repetitiveTask) {
        this.repetitiveTask = repetitiveTask;
    }

    public PlantInformation getPlantInformation() {
        return plantInformation;
    }

    public void setPlantInformation(PlantInformation plantInformation) {
        this.plantInformation = plantInformation;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
