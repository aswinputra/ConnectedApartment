package com.sepgroup4.connectedapartment.Model;

/**
 * Created by kiman on 14/10/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Booking {

    @SerializedName("Id")
    @Expose
    public Integer id;
    @SerializedName("FacilityId")
    @Expose
    public Integer facilityId;
    @SerializedName("PersonId")
    @Expose
    public Integer personId;
    @SerializedName("StartTime")
    @Expose
    public String startTime;
    @SerializedName("EndTime")
    @Expose
    public String endTime;

    public Booking(Integer id, Integer facilityId, Integer personId, String startTime, String endTime) {
        this.id = id;
        this.facilityId = facilityId;
        this.personId = personId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}