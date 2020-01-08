package groentjes.onzeMoestuin.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TaskPlantInfo {

    private Integer daysAfterStart;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plantInfo_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PlantInformation plantInformation;
}
