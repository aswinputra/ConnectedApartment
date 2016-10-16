package com.sepgroup4.connectedapartment.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiman on 14/10/2016.
 */

public class MakeBookingResponse extends RequestResponse{

    private List<Booking> Result = new ArrayList<>();

    public MakeBookingResponse(Boolean isSuccess, String message, List<Booking> result) {
        super(isSuccess, message);
        Result = result;
    }

    public List<Booking> getResult() {
        return Result;
    }

    public void setResult(List<Booking> result) {
        Result = result;
    }
}
