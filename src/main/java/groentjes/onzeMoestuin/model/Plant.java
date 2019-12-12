package groentjes.onzeMoestuin.model;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;

/**
 * @author Patricia Orriens-Spuij and Eric van Dalen
 * The Plant class concerns a plant placed in the garden of a user(users).
 */
@Entity
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer plantId;

    private Point coordinate;
    private boolean sown;
    private Date startDate;

//    @ManyToOne
    private Garden garden;

    //foreign key
    private PlantInformation plantInfo;

    // getters and setters
    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    public boolean isSown() {
        return sown;
    }

    public void setSown(boolean sown) {
        this.sown = sown;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Garden getGarden() {
        return garden;
    }

    public void setGarden(Garden garden) {
        this.garden = garden;
    }

    public PlantInformation getPlantInfo() {
        return plantInfo;
    }

    public void setPlantInfo(PlantInformation plantInfo) {
        this.plantInfo = plantInfo;
    }
}
