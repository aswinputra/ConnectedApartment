package com.sepgroup4.connectedapartment.Model;

/**
 * Created by kiman on 14/10/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Facility {

    @SerializedName("Id")
    @Expose
    public Integer id;
    @SerializedName("Level")
    @Expose
    public String level;
    @SerializedName("Number")
    @Expose
    public String number;
    @SerializedName("BuildingId")
    @Expose
    public Integer buildingId;

    public Facility(Integer id, String level, String number, Integer buildingId) {
        this.id = id;
        this.level = level;
        this.number = number;
        this.buildingId = buildingId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getFacilityName() {
        return level + "." + number;
    }
}