package groentjes.onzeMoestuin.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;
import java.util.Set;

/**
 * @author Patricia Orriens-Spuij, Wim Kruizinga and Eric van Dalen
 * The Plant class concerns a plant placed in the garden of a user(users).
 */
@Entity
@JsonIgnoreProperties({"garden"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "plantId")
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer plantId;

    private boolean sown;

    @JsonFormat(pattern="dd-MM-yyyy")
    private Date startDate;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date harvestDate;

    // Location & size in garden
    private int xCoordinate;
    private int yCoordinate;
    private int width;
    private int height;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "garden_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Garden garden;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "plantInfo_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PlantInformation plantInformation;

    public boolean isOwnerOfPlant(User user) {
        return this.getGarden().isGardenMember(user);
    }

    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
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

    public Date getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(Date harvestDate) {
        this.harvestDate = harvestDate;
    }

    public Garden getGarden() {
        return garden;
    }

    public void setGarden(Garden garden) {
        this.garden = garden;
    }

    public PlantInformation getPlantInformation() {
        return plantInformation;
    }

    public void setPlantInformation(PlantInformation plantInformation) {
        this.plantInformation = plantInformation;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
