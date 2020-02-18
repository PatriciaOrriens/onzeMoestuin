package groentjes.onzeMoestuin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"tasks"})
public class PlantInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer plantInfoId;

    @NotEmpty
    private String plantName;
    private String latinName;
    private Integer plantingDistance;
    private String lighting;
    private String soilType;
    private String sowingStart;
    private String sowingEnd;
    private String plantingStart;
    private String plantingEnd;
    private String harvestingStart;
    private String harvestingEnd;
    private Integer growTime;
    private String imageName;

    @Lob
    private byte[] image;

    @OneToMany(
            mappedBy = "plantInformation",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<TaskPlantInfo> tasks = new HashSet<>();

    public PlantInformation() {
    }

    // getters and setters
    public Integer getPlantInfoId() {
        return plantInfoId;
    }

    public Set<TaskPlantInfo> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TaskPlantInfo> tasks) {
        this.tasks = tasks;
    }

    public void setPlantInfoId(Integer plantInfoId) {
        this.plantInfoId = plantInfoId;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public Integer getPlantingDistance() {
        return plantingDistance;
    }

    public void setPlantingDistance(Integer plantingDistance) {
        this.plantingDistance = plantingDistance;
    }

    public String getLighting() {
        return lighting;
    }

    public void setLighting(String lighting) {
        this.lighting = lighting;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public String getSowingStart() {
        return sowingStart;
    }

    public void setSowingStart(String sowingStart) {
        this.sowingStart = sowingStart;
    }

    public String getSowingEnd() {
        return sowingEnd;
    }

    public void setSowingEnd(String sowingEnd) {
        this.sowingEnd = sowingEnd;
    }

    public String getPlantingStart() {
        return plantingStart;
    }

    public void setPlantingStart(String plantingStart) {
        this.plantingStart = plantingStart;
    }

    public String getPlantingEnd() {
        return plantingEnd;
    }

    public void setPlantingEnd(String plantingEnd) {
        this.plantingEnd = plantingEnd;
    }

    public String getHarvestingStart() {
        return harvestingStart;
    }

    public void setHarvestingStart(String harvestingStart) {
        this.harvestingStart = harvestingStart;
    }

    public String getHarvestingEnd() {
        return harvestingEnd;
    }

    public void setHarvestingEnd(String harvestingEnd) {
        this.harvestingEnd = harvestingEnd;
    }

    public Integer getGrowTime() {
        return growTime;
    }

    public void setGrowTime(Integer growTime) {
        this.growTime = growTime;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
