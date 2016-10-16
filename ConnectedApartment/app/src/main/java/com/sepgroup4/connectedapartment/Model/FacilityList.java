package com.sepgroup4.connectedapartment.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kiman on 16/10/2016.
 */

public class FacilityList {
    private Map<String, String> mFacilityMap;

    public FacilityList(FacilityResponse requestResponse) {
        mFacilityMap = new HashMap<>();
        for(Facility facilities : requestResponse.getResult()){
            mFacilityMap.put(facilities.getId().toString(),facilities.getFacilityName());
        }
    }

    public int getFacilityId(String facilityName) {
        String id = "";
        for (Map.Entry<String, String> facilityMap : mFacilityMap.entrySet()){
            if (facilityMap.getValue().equals(facilityName)){
                id = facilityMap.getKey();
            }
        }
        return Integer.parseInt(id);
    }

    public String getFacilityName(int id){
        String facilityId = String.valueOf(id);
        for (Map.Entry<String, String> facility : mFacilityMap.entrySet()){
            if(facility.getKey().equals(facilityId)){
                return facility.getValue();
            }
        }
        return null;
    }
}
