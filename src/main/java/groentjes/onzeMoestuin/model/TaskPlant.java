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
public class TaskPlant implements Comparable<TaskPlant> {

    private final static long HOURSINDAY = 24;
    private final static long MILLISECONDSINHOUR = 3600000;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskPlantId;

    private String dueDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plantId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Plant plant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "taskPlantInfoId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TaskPlantInfo taskPlantInfo;

    private String completedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "completedByUserId", referencedColumnName = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public void calculateDueDate() {
        long addedMilliseconds = this.getTaskPlantInfo().getDaysAfterStart() * HOURSINDAY * MILLISECONDSINHOUR;
        long beginning = this.getPlant().getStartDate().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.dueDate = sdf.format(new Date(beginning + addedMilliseconds));
    }

    public String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }

    public Integer getTaskPlantId() {
        return taskPlantId;
    }

    public void setTaskPlantId(Integer taskPlantId) {
        this.taskPlantId = taskPlantId;
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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public int compareTo(TaskPlant otherTaskPlant) {
        return this.dueDate.compareTo(otherTaskPlant.dueDate);
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate = completedDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
