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

    private final static long HOURS_IN_DAY = 24;
    private final static long MILLISECONDS_IN_HOUR = 3600000;
    private final static int YEAR_INDEX = 2;
    private final static int MONTH_INDEX = 1;
    private final static int DAY_INDEX = 0;

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
        long addedMilliseconds = this.getTaskPlantInfo().getDaysAfterStart() * HOURS_IN_DAY * MILLISECONDS_IN_HOUR;
        long beginning = this.getPlant().getStartDate().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        this.dueDate = sdf.format(new Date(beginning + addedMilliseconds));
    }

    public String getStringFromDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(date);
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

        String[] thisDate = this.dueDate.split("-");
        String[] otherDate = otherTaskPlant.dueDate.split("-");

        if (thisDate[YEAR_INDEX].compareTo(otherDate[YEAR_INDEX]) != 0){
            return thisDate[YEAR_INDEX].compareTo(otherDate[YEAR_INDEX]);
        } else if (thisDate[MONTH_INDEX].compareTo(otherDate[MONTH_INDEX]) != 0) {
            return thisDate[MONTH_INDEX].compareTo(otherDate[MONTH_INDEX]);
        } else {
            return thisDate[DAY_INDEX].compareTo(otherDate[DAY_INDEX]);
        }
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
