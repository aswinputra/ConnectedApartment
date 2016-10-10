package com.sepgroup4.connectedapartment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Apartment {

    @SerializedName("TenantsAllowed")
    @Expose
    public Integer tenantsAllowed;
    @SerializedName("FacingDirection")
    @Expose
    public String facingDirection;
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

    public Apartment(Integer tenantsAllowed, String facingDirection, Integer id, String level, String number, Integer buildingId) {
        this.tenantsAllowed = tenantsAllowed;
        this.facingDirection = facingDirection;
        this.id = id;
        this.level = level;
        this.number = number;
        this.buildingId = buildingId;
    }

    public Integer getTenantsAllowed() {
        return tenantsAllowed;
    }

    public String getFacingDirection() {
        return facingDirection;
    }

    public Integer getId() {
        return id;
    }

    public String getStringId(){
        return String.valueOf(id);
    }

    public String getLevel() {
        return level;
    }

    public String getNumber() {
        return number;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public String getApartmentName() {
        return level + "." + number;
    }
}
