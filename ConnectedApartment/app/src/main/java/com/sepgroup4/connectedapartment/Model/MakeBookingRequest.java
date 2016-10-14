package com.sepgroup4.connectedapartment.Model;

/**
 * Created by kiman on 14/10/2016.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MakeBookingRequest {
    @SerializedName("FacilityId")
    @Expose
    public Integer facilityId;
    @SerializedName("StartTime")
    @Expose
    public String startTime;
    @SerializedName("EndTime")
    @Expose
    public String endTime;

    public MakeBookingRequest(Integer facilityId, String startTime, String endTime) {
        this.facilityId = facilityId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
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