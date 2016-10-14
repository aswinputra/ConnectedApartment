package com.sepgroup4.connectedapartment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TenantInfo {

    @SerializedName("ApartmentId")
    @Expose
    public Integer apartmentId;
    @SerializedName("BuildingId")
    @Expose
    public Integer buildingId;
    @SerializedName("Id")
    @Expose
    public Integer id;
    @SerializedName("FirstName")
    @Expose
    public String firstName;
    @SerializedName("LastName")
    @Expose
    public String lastName;
    @SerializedName("DoB")
    @Expose
    public String doB;
    @SerializedName("Phone")
    @Expose
    public String phone;
    @SerializedName("UserId")
    @Expose
    public String userId;

    public TenantInfo(Integer apartmentId, Integer buildingId, Integer id, String firstName, String lastName, String doB, String phone, String userId) {
        this.apartmentId = apartmentId;
        this.buildingId = buildingId;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.doB = doB;
        this.phone = phone;
        this.userId = userId;
    }

    public Integer getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(Integer apartmentId) {
        this.apartmentId = apartmentId;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDoB() {
        return doB;
    }

    public void setDoB(String doB) {
        this.doB = doB;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}