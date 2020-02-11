package groentjes.onzeMoestuin.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Integer.parseInt;

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
    private final static int DATE_STRING_LENGTH = 10;
    private final static  String DATE_MATCH = "\\d{2}-\\d{2}-\\d{4}";
    private final static int DAYS_IN_FEBRUARY = 28;
    private final static int DAYS_IN_SHORT_MONTH = 30;
    private final static int DAYS_IN_LARGE_MONTH = 31;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;

    @Size(min = 10, max=10, message = "Vervaldatum moet het patroon dd-mm-jjjj hebben")
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

        if (thisDate[YEAR_INDEX].compareTo(otherDate[YEAR_INDEX]) != DAY_INDEX){
            return thisDate[YEAR_INDEX].compareTo(otherDate[YEAR_INDEX]);
        } else if (thisDate[MONTH_INDEX].compareTo(otherDate[MONTH_INDEX]) != DAY_INDEX) {
            return thisDate[MONTH_INDEX].compareTo(otherDate[MONTH_INDEX]);
        } else {
            return thisDate[DAY_INDEX].compareTo(otherDate[DAY_INDEX]);
        }
    }

    public String getStringFromDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return simpleDateFormat.format(date);
    }

    // does not account for leap years
    public boolean isDateString(String dateString) {
        if (dateString.length() != DATE_STRING_LENGTH || !dateString.matches(DATE_MATCH)) {
            return false;
        } else {
            String[] dateSplit = dateString.split("-");
            try {
                parseInt(dateSplit[2]);
                return isDayAndMonthCorrect(parseInt(dateSplit[0]), parseInt(dateSplit[1]));
            } catch (Exception exception) {
                return false;
            }
        }
    }

    private boolean isDayAndMonthCorrect(int day, int  month) {
        if ((month > 0 && month <= 12) && day > 0) {
            int numberOfDaysInMonth = giveDaysInMonth(month);
            return day <= numberOfDaysInMonth;
        }
        return false;
    }

    private int giveDaysInMonth(int month) {
        switch(month) {
            case 2: return DAYS_IN_FEBRUARY;
            case 4:
            case 6:
            case 9:
            case 11: return DAYS_IN_SHORT_MONTH;
            default: return DAYS_IN_LARGE_MONTH;
        }
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
