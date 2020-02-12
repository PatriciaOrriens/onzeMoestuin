package groentjes.onzeMoestuin.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Eric van Dalen and Gjalt Wybenga
 * The Taskplant class concerns tasks to be performed for specific plants
 */
@Entity
public class TaskPlant extends Task  {

    private final static long HOURS_IN_DAY = 24;
    private final static long MILLISECONDS_IN_HOUR = 3600000;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plantId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Plant plant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "taskPlantInfoId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TaskPlantInfo taskPlantInfo;


    public void calculateDueDate() {
        long addedMilliseconds = this.getTaskPlantInfo().getDaysAfterStart() * HOURS_IN_DAY * MILLISECONDS_IN_HOUR;
        long beginning = this.getPlant().getStartDate().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        this.setDueDate(sdf.format(new Date(beginning + addedMilliseconds)));
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
