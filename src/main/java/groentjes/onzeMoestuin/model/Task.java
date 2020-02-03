package groentjes.onzeMoestuin.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Eric van Dalen
 * This class is the super class for all kind of tasks in connection with gardening
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Task implements Comparable<Task> {

    private final static int YEAR_INDEX = 2;
    private final static int MONTH_INDEX = 1;
    private final static int DAY_INDEX = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;

    private String dueDate;

    private String completedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "completedByUserId", referencedColumnName = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;


    @Override
    public int compareTo(Task otherTask) {

        String[] thisDate = this.dueDate.split("-");
        String[] otherDate = otherTask.dueDate.split("-");

        if (thisDate[YEAR_INDEX].compareTo(otherDate[YEAR_INDEX]) != 0){
            return thisDate[YEAR_INDEX].compareTo(otherDate[YEAR_INDEX]);
        } else if (thisDate[MONTH_INDEX].compareTo(otherDate[MONTH_INDEX]) != 0) {
            return thisDate[MONTH_INDEX].compareTo(otherDate[MONTH_INDEX]);
        } else {
            return thisDate[DAY_INDEX].compareTo(otherDate[DAY_INDEX]);
        }
    }

    public String getStringFromDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(date);
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
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
