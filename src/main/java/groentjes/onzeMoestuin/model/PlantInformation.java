package groentjes.onzeMoestuin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Month;

@Entity
public class PlantInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer plantInfoId;

    private String plantName;
    private String latinName;
    private Integer plantingDistance;
    private String lighting;
    private String soilType;
    private Month sowingStart;
    private Month sowingEnd;
    private Month plantingStart;
    private Month plantingEnd;
    private Month harvestingStart;
    private Month harvestingEnd;
    private Integer growTimeDays;
    // private Image image;

    // getters and setters
    public Integer getPlantInfoId() {
        return plantInfoId;
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

    public Month getSowingStart() {
        return sowingStart;
    }

    public void setSowingStart(Month sowingStart) {
        this.sowingStart = sowingStart;
    }

    public Month getSowingEnd() {
        return sowingEnd;
    }

    public void setSowingEnd(Month sowingEnd) {
        this.sowingEnd = sowingEnd;
    }

    public Month getPlantingStart() {
        return plantingStart;
    }

    public void setPlantingStart(Month plantingStart) {
        this.plantingStart = plantingStart;
    }

    public Month getPlantingEnd() {
        return plantingEnd;
    }

    public void setPlantingEnd(Month plantingEnd) {
        this.plantingEnd = plantingEnd;
    }

    public Month getHarvestingStart() {
        return harvestingStart;
    }

    public void setHarvestingStart(Month harvestingStart) {
        this.harvestingStart = harvestingStart;
    }

    public Month getHarvestingEnd() {
        return harvestingEnd;
    }

    public void setHarvestingEnd(Month harvestingEnd) {
        this.harvestingEnd = harvestingEnd;
    }

    public Integer getGrowTimeDays() {
        return growTimeDays;
    }

    public void setGrowTimeDays(Integer growTimeDays) {
        this.growTimeDays = growTimeDays;
    }
}
