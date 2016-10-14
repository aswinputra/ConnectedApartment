package com.sepgroup4.connectedapartment.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiman on 14/10/2016.
 */

public class FacilityResponse extends RequestResponse{
    private List<Facility> Result = new ArrayList<>();

    public FacilityResponse(Boolean isSuccess, String message, List<Facility> result) {
        super(isSuccess, message);
        Result = result;
    }

    public List<Facility> getResult() {
        return Result;
    }

    public void setResult(List<Facility> result) {
        Result = result;
    }
}
