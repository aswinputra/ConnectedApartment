package com.sepgroup4.connectedapartment.Rest;

import com.sepgroup4.connectedapartment.Model.RegisterRequest;
import com.sepgroup4.connectedapartment.Model.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {

    @FormUrlEncoded
    @POST("Token")
    Call<RegisterResponse> registerTenant(@Body RegisterRequest registerRequest);
//
//    @POST("facility/book")
//    Call<FacilityBookingResponse> bookFacility(@Body FacilityBookingRequest facilityBookingRequest);
//
//    @GET("facility/status")
//    Call<FacilityStatus> getFacilityStatus(@Body FacilityStatusRequest facilityStatusRequest);

}




