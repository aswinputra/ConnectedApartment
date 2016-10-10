package com.sepgroup4.connectedapartment.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kiman on 10/10/2016.
 */

public class ApartmentResponse extends RequestResponse{
    public List<Apartment> Result = new ArrayList<>();

    public ApartmentResponse(Boolean isSuccess, String message, List<Apartment> result) {
        super(isSuccess, message);
        Result = result;
    }

    public List<Apartment> getApartments() {
        return Result;
    }

}
