package com.sepgroup4.connectedapartment.Model;

/**
 * Created by kiman on 15/10/2016.
 */

public class SingleFacilityResponse extends RequestResponse {

    private Facility Result;

    public SingleFacilityResponse(Boolean isSuccess, String message, Facility result) {
        super(isSuccess, message);
        Result = result;
    }

    public Facility getResult() {
        return Result;
    }

    public void setResult(Facility result) {
        Result = result;
    }
}
